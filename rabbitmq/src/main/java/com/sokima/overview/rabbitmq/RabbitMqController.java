package com.sokima.overview.rabbitmq;

import com.sokima.overview.rabbitmq.message.RabbitMqMessage;
import com.sokima.overview.rabbitmq.producer.RabbitMqJsonMessageProducer;
import com.sokima.overview.rabbitmq.producer.RabbitMqSimpleMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rabbitmq")
public class RabbitMqController {

    @Autowired
    RabbitMqSimpleMessageProducer rabbitMqSimpleMessageProducer;

    @Autowired
    RabbitMqJsonMessageProducer rabbitMqJsonMessageProducer;

    @GetMapping("send/{messageContent}")
    public ResponseEntity<String> sendSimpleMessage(@PathVariable String messageContent) {
        rabbitMqSimpleMessageProducer.sendMessage(messageContent);
        return ResponseEntity.ok(messageContent);
    }

    @PostMapping("send")
    public ResponseEntity<RabbitMqMessage> sendJsonMessage(@RequestBody RabbitMqMessage messageContent) {
        rabbitMqJsonMessageProducer.sendMessage(messageContent);
        return ResponseEntity.ok(messageContent);
    }
}
