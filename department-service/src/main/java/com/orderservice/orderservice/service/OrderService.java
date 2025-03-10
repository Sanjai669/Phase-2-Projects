package com.orderservice.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderservice.orderservice.entity.Order;
import com.orderservice.orderservice.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    public Order saveOrder(Order order) {
        log.info("Inside saveOrder of OrderService");
        return orderRepository.save(order);
    }

    public Order findOrderById(Long orderId) {
        log.info("Inside findOrderById of OrderService");
        return orderRepository.findByOrderId(orderId);
    }
}
