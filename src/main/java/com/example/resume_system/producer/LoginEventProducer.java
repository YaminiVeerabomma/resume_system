package com.example.resume_system.producer;

import com.example.resume_system.DTO.LoginEventMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendLoginEvent(LoginEventMessage message) {

        rabbitTemplate.convertAndSend(
                "user.login.exchange",
                "user.login.routing",
                message
        );

        System.out.println("📤 [PRODUCER] Login Event Sent → " + message.getEmail());
    }
}
