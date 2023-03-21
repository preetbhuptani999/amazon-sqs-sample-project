package tbd.health.exercise.sqs;

import com.fasterxml.jackson.databind.JsonNode;
import io.reflectoring.sqs.api.SqsMessageHandler;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import tbd.health.exercise.domain.Customer;
import tbd.health.exercise.domain.Order;
import tbd.health.exercise.domain.OrderDetails;
import tbd.health.exercise.domain.Product;
import tbd.health.exercise.domain.enums.Status;
import tbd.health.exercise.dto.OrderDto;
import tbd.health.exercise.repositories.CustomerRepository;
import tbd.health.exercise.repositories.OrderDetailsRepository;
import tbd.health.exercise.repositories.OrderRepository;
import tbd.health.exercise.repositories.ProductRepository;

public class MessageHandler implements SqsMessageHandler<OrderDto> {

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    private final OrderDetailsRepository orderDetailsRepository;

    private final ProductRepository productRepository;

    private final RestTemplate restTemplate;

    public MessageHandler(CustomerRepository customerRepository, OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, ProductRepository productRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void handle(OrderDto orderDto) {
        // Create a new record in Orders table
        long customerId = orderDto.getCustomerId();
        Customer customer = customerRepository.findCustomerById(customerId);
        Order order = orderRepository.save(new Order(customer));

        for (Long productId: orderDto.getProductIds()) {
            // Create a new record in OrderDetails table
            Product product = productRepository.findProductById(productId);
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(order);
            orderDetails.setProduct(product);
            orderDetails.setStatus(Status.PENDING_FULFILL);
            orderDetailsRepository.save(orderDetails);

            // Call third party API
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // We need to pass all the required fields along with order id and product id in the body.
            // I am passing blank string for now as we don't need to make an actual API call.
            String body = "";

            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    "https://api.chronomics.com/orders/v2",
                    HttpMethod.POST,
                    entity,
                    JsonNode.class
            );
            if (response.getStatusCodeValue() == 200) {
                orderDetails.setStatus(Status.SENT_TO_LAB);
                orderDetailsRepository.save(orderDetails);
            } else {
                // Log error and throw an exception, so it does not get deleted from SQS and retried after some time.
            }
        }
    }

    @Override
    public Class<OrderDto> messageType() {
        return OrderDto.class;
    }
}
