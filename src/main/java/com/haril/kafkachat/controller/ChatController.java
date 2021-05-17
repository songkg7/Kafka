package com.haril.kafkachat.controller;

import com.haril.kafkachat.constants.KafkaConstants;
import com.haril.kafkachat.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class ChatController {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    @PostMapping("/publish")
    public void sendMessage(@RequestBody Message message) {
        log.info("Produce message : " + message.toString());
        // TODO: 시간 세팅은 포맷팅해서 넣기
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        return message;
    }

}
