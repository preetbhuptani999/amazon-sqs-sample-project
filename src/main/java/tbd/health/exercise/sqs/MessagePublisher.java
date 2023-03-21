package tbd.health.exercise.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reflectoring.sqs.api.SqsMessagePublisher;
import tbd.health.exercise.dto.OrderDto;

public class MessagePublisher extends SqsMessagePublisher<OrderDto> {

    public MessagePublisher(AmazonSQS sqsClient, ObjectMapper objectMapper) {
        super("http://sqsQueueUrl", sqsClient, objectMapper);
    }

}