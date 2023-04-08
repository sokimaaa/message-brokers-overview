package com.sokima.overview.activemq.producer;

import com.sokima.overview.activemq.message.ActiveMqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqProducer {

    private static final Logger log = LoggerFactory.getLogger(ActiveMqProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${message-brokers-overview.activemq.json-topic}")
    private String jsonTopic;

    @Value("${message-brokers-overview.activemq.simple-topic}")
    private String simpleTopic;

    public void sendJsonMessage(ActiveMqMessage message) {
        send(jsonTopic, message);
    }

    public void sendSimpleMessage(String message) {
        send(simpleTopic, message);
    }

    private <T> void send(String topic, T message) {
        try {
            jmsTemplate.convertAndSend(topic, message);
            log.info("Message {} was sent successfully.", message);
        } catch (Exception ex) {
            log.error("Message {} was not sent.", message);
            log.error("Message was not sent with error {}.", ex.getMessage());
        }
    }
}
