package com.sokima.overview.rabbitmq.consumer;

import com.sokima.overview.rabbitmq.message.RabbitMqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @RabbitListener(queues = "${message-brokers-overview.rabbitmq.queue.name.simple}")
    public void processJsonMessage(String message) {
        log.info("Received message {}.", message);
    }

    @RabbitListener(queues = "${message-brokers-overview.rabbitmq.queue.name.json}")
    public void processSimpleMessage(RabbitMqMessage message) {
        log.info("Received message {}.", message);
    }
}
