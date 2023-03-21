package tbd.health.exercise.service.impl;

import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import tbd.health.exercise.dto.OrderDto;
import tbd.health.exercise.service.UserService;
import tbd.health.exercise.sqs.MessagePublisher;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void createOrder(OrderDto orderDto) {

        // The below code is used to send the order details to Amazon SQS
        MessagePublisher messagePublisher = new MessagePublisher(AmazonSQSClientBuilder.standard().build(), new ObjectMapper());
        messagePublisher.publish(orderDto);
    }

    @Override
    public void updateOrder(String orderDetails) {

//        we need to update order status in order details table. I am not sure how exactly
//        the data is returned in the webhook call so am not implementing the entire logic.
    }
}
