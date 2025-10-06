package com.ms.product.consumers;

import com.ms.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductConsumer {

    private final ProductService productService;

    @RabbitListener(queues = "${broker.queue.product.request}")
    public void receiveProductResponse(String message) {
        log.info("Receive a new message for CloudAMQP: {}", message);
        productService.updateProductAmmount(message);
    }

}
