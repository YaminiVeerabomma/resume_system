package com.example.resume_system.producer;

import com.example.resume_system.DTO.UserRegisterMessage;
import com.example.resume_system.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendRegisterEvent(UserRegisterMessage message) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.REGISTER_EXCHANGE,
                RabbitMQConfig.REGISTER_ROUTING_KEY,
                message
        );

        System.out.println("📤 [PRODUCER] User register event sent → " + message.getEmail());
    }
}
