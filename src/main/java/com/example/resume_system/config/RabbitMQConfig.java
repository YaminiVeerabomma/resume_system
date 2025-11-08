package com.example.resume_system.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

    // ✅ Registration Event
    public static final String REGISTER_QUEUE = "user.register.queue";
    public static final String REGISTER_EXCHANGE = "user.register.exchange";
    public static final String REGISTER_ROUTING_KEY = "user.register.routing";

    // ✅ Login Event
    public static final String LOGIN_QUEUE = "user.login.queue";
    public static final String LOGIN_EXCHANGE = "user.login.exchange";
    public static final String LOGIN_ROUTING_KEY = "user.login.routing";
	

    // ✅ Register Queue
    @Bean
    public Queue registerQueue() {
        return new Queue(REGISTER_QUEUE, true);
    }

    // ✅ Register Exchange
    @Bean
    public TopicExchange registerExchange() {
        return new TopicExchange(REGISTER_EXCHANGE);
    }

    // ✅ Register Binding
    @Bean
    public Binding registerBinding() {
        return BindingBuilder.bind(registerQueue())
                .to(registerExchange())
                .with(REGISTER_ROUTING_KEY);
    }

    // ✅ Login Queue
    @Bean
    public Queue loginQueue() {
        return new Queue(LOGIN_QUEUE, true);
    }

    // ✅ Login Exchange
    @Bean
    public TopicExchange loginExchange() {
        return new TopicExchange(LOGIN_EXCHANGE);
    }

    // ✅ Login Binding
    @Bean
    public Binding loginBinding() {
        return BindingBuilder.bind(loginQueue())
                .to(loginExchange())
                .with(LOGIN_ROUTING_KEY);
    }

    // ✅ Convert messages to JSON (IMPORTANT)
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // ✅ Attach JSON converter to RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
