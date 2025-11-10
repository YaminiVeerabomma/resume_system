package com.example.resume_system.consumer;



import com.example.resume_system.DTO.UserRegisterMessage;
import com.example.resume_system.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterConsumer {

    // ✅ This automatically listens to the queue
    @RabbitListener(queues = RabbitMQConfig.REGISTER_QUEUE )
    public void handleUserRegistration(UserRegisterMessage message) {

        System.out.println("📩 [CONSUMER] New User Registration Event Received");
        System.out.println("➡ Name: " + message.getName());
        System.out.println("➡ Email: " + message.getEmail());

        // Example action (real-time):
        System.out.println("📧 Sending welcome email to " + message.getEmail());

        // Simulating email sending
        try { Thread.sleep(1000); } catch (Exception ignored) {}

        System.out.println("✅ Welcome email sent to " + message.getEmail());
    }
}
