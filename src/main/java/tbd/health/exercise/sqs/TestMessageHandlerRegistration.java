package tbd.health.exercise.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reflectoring.sqs.api.SqsMessageHandler;
import io.reflectoring.sqs.api.SqsMessageHandlerProperties;
import io.reflectoring.sqs.api.SqsMessageHandlerRegistration;
import io.reflectoring.sqs.api.SqsMessagePollerProperties;
import org.springframework.stereotype.Component;
import tbd.health.exercise.dto.OrderDto;

import java.time.Duration;

@Component
public class TestMessageHandlerRegistration implements SqsMessageHandlerRegistration<OrderDto> {

    private final AmazonSQS client;
    private final ObjectMapper objectMapper;
    private final MessageHandler messageHandler;

    public TestMessageHandlerRegistration(
            AmazonSQS client,
            ObjectMapper objectMapper,
            MessageHandler messageHandler) {
        this.client = client;
        this.objectMapper = objectMapper;
        this.messageHandler = messageHandler;
    }

    @Override
    public SqsMessageHandler<OrderDto> messageHandler() {
        return this.messageHandler;
    }

    @Override
    public String name() {
        return "testMessageHandler";
    }

    @Override
    public SqsMessageHandlerProperties messageHandlerProperties() {
        return new SqsMessageHandlerProperties();
    }

    @Override
    public SqsMessagePollerProperties messagePollerProperties() {
        return new SqsMessagePollerProperties("http://sqsQueueUrl").withWaitTime(Duration.ofSeconds(1800L)).withBatchSize(1);
    }

    @Override
    public AmazonSQS sqsClient() {
        return this.client;
    }

    @Override
    public ObjectMapper objectMapper() {
        return this.objectMapper;
    }
}
