package com.sokima.overview.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${message-brokers-overview.kafka.topic}")
    private String topic;

    public void sendMessage(String message) {
        try {
            kafkaTemplate.send(topic, message);
            log.info("Message {} was sent successfully.", message);
        } catch (Exception ex) {
            log.error("Message {} was not sent.", message);
            log.error("Message was not sent with error {}.", ex.getMessage());
        }
    }
}
