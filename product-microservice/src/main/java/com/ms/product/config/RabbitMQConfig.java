package com.ms.product.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.product.request}")
    private String productRequestQueue;

    @Value("${broker.queue.product.response}")
    private String productResponseQueue;

    @Value("${broker.exchange.product}")
    private String productExchange;

    @Value("${broker.routing-key.product.request}")
    private String productRequestRoutingKey;

    @Value("${broker.routing-key.product.response}")
    private String productResponseRoutingKey;

    @Bean
    public Queue productRequestQueue() {
        return new Queue(productRequestQueue, true);
    }

    @Bean
    public Queue productResponseQueue() {
        return new Queue(productResponseQueue, true);
    }

    @Bean
    public DirectExchange productExchange() {
        return new DirectExchange(productExchange);
    }

    @Bean
    public Binding bindingProductRequest() {
        return BindingBuilder
                .bind(productRequestQueue())
                .to(productExchange())
                .with(productRequestRoutingKey);
    }

    @Bean
    public Binding bindingProductResponse() {
        return BindingBuilder
                .bind(productResponseQueue())
                .to(productExchange())
                .with(productResponseRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
