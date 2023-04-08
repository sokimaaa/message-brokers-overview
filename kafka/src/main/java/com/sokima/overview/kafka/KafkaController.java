package com.sokima.overview.kafka;

import com.sokima.overview.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaController {

    @Autowired
    KafkaProducer kafkaProducer;

    @GetMapping("publish/{messageContent}")
    public ResponseEntity<String> publish(@PathVariable String messageContent) {
        kafkaProducer.sendMessage(messageContent);
        return ResponseEntity.ok(messageContent);
    }

}
