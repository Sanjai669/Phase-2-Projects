package com.orderservice.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderservice.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderId(Long orderId);
}
