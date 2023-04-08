package com.sokima.overview.activemq.consumer;

import com.sokima.overview.activemq.message.ActiveMqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.ObjectMessage;

@Component
public class ActiveMqConsumer {

    private static final Logger log = LoggerFactory.getLogger(ActiveMqConsumer.class);

    @JmsListener(destination = "${message-brokers-overview.activemq.json-topic}")
    public void processJsonMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            ActiveMqMessage activeMqMessage = (ActiveMqMessage) objectMessage.getObject();
            log.info("Received message {}.", activeMqMessage);
        } catch (Exception ex) {
            log.error("Failed receiving message {}.", message);
        }
    }

    @JmsListener(destination = "${message-brokers-overview.activemq.simple-topic}")
    public void processSimpleMessage(String message) {
        log.info("Received message {}.", message);
    }
}
