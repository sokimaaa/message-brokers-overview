package com.sokima.overview.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqSimpleMessageProducer implements RabbitMqMessageProducer<String> {

    private static final Logger log = LoggerFactory.getLogger(RabbitMqSimpleMessageProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${message-brokers-overview.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${message-brokers-overview.rabbitmq.routing-key.simple}")
    private String simpleRoutingKey;

    @Override
    public void sendMessage(String message) {
        try {
            rabbitTemplate.convertAndSend(exchangeName, simpleRoutingKey, message);
            log.info("Message {} was sent successfully.", message);
        } catch (Exception ex) {
            log.error("Message {} was not sent.", message);
            log.error("Message was not sent with error {}.", ex.getMessage());
        }
    }
}
