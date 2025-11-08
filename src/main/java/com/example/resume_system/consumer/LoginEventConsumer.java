package com.example.resume_system.consumer;

import com.example.resume_system.DTO.LoginEventMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class LoginEventConsumer {

    @RabbitListener(queues = "user.login.queue")
    public void handleLoginEvent(LoginEventMessage message) {

        System.out.println("📩 [CONSUMER] Login Event Received");
        System.out.println("➡ Email: " + message.getEmail());
        System.out.println("➡ Login Time: " + message.getLoginTime());
    

        // ✅ Real use: save to DB / send alert
        System.out.println("💾 Saving login history...");
    }
}
