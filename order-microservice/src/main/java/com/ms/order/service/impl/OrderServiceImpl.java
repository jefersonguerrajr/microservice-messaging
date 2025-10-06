package com.ms.order.service.impl;

import com.ms.order.exception.NotFoundException;
import com.ms.order.model.Order;
import com.ms.order.repository.OrderRepository;
import com.ms.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ResponseEntity<List<Order>> listAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderRepository.findAll());
    }

    @Override
    public ResponseEntity<Order> findOrderById(long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found")));
    }

    @Override
    public ResponseEntity<Order> saveOrder(Order order) {
        return ResponseEntity.status(HttpStatus.OK).body(orderRepository.save(order));
    }

    @Override
    public ResponseEntity<Order> updateOrder(Order order) {
        return null;
    }

    @Override
    public ResponseEntity<Order> removeOrder(long orderId) {
        return null;
    }

}
