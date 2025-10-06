package com.ms.order.service;

import com.ms.order.model.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    ResponseEntity<List<Order>> listAllOrders();

    ResponseEntity<Order> findOrderById(long id);

    ResponseEntity<Order> saveOrder(Order order);

    ResponseEntity<Order> updateOrder(Order order);

    ResponseEntity<Order> removeOrder(long orderId);

}
