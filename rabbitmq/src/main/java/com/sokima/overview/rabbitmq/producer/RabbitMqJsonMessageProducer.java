package com.sokima.overview.rabbitmq.producer;

import com.sokima.overview.rabbitmq.message.RabbitMqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqJsonMessageProducer implements RabbitMqMessageProducer<RabbitMqMessage> {

    private static final Logger log = LoggerFactory.getLogger(RabbitMqJsonMessageProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${message-brokers-overview.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${message-brokers-overview.rabbitmq.routing-key.json}")
    private String jsonRoutingKey;

    @Override
    public void sendMessage(RabbitMqMessage message) {
        try {
            rabbitTemplate.convertAndSend(exchangeName, jsonRoutingKey, message);
            log.info("Message {} was sent successfully.", message);
        } catch (Exception ex) {
            log.error("Message {} was not sent.", message);
            log.error("Message was not sent with error {}.", ex.getMessage());
        }
    }
}
