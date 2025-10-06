package com.ms.product.produces;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductProduces {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.exchange.product}")
    private String exchange;

    @Value("${broker.routing-key.product.response}")
    private String routingKey;

    public void sendProductRequest(Object request) {
        log.info("Sending message for CloudAMQP: {}", request);
        rabbitTemplate.convertAndSend(exchange, routingKey, request);
    }

}
