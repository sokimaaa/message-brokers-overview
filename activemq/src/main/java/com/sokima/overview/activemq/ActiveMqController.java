package com.sokima.overview.activemq;

import com.sokima.overview.activemq.message.ActiveMqMessage;
import com.sokima.overview.activemq.producer.ActiveMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("activemq")
public class ActiveMqController {

    @Autowired
    ActiveMqProducer activeMqProducer;

    @GetMapping("send/{messageContent}")
    public ResponseEntity<String> sendSimpleMessage(@PathVariable String messageContent) {
        activeMqProducer.sendSimpleMessage(messageContent);
        return ResponseEntity.ok(messageContent);
    }

    @PostMapping("send")
    public ResponseEntity<ActiveMqMessage> sendJsonMessage(@RequestBody ActiveMqMessage messageContent) {
        activeMqProducer.sendJsonMessage(messageContent);
        return ResponseEntity.ok(messageContent);
    }
}
