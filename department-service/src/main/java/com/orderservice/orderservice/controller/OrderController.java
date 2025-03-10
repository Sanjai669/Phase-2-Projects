package com.orderservice.orderservice.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.orderservice.orderservice.entity.Order;
import com.orderservice.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private static final String ORDER_SERVICE = "orderService";

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public Order saveOrder(@RequestBody Order order) {
        log.info("Inside saveOrder method of OrderController");
        return orderService.saveOrder(order);
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable("id") Long orderId) {
        log.info("Inside findOrderById method of OrderController");
        return orderService.findOrderById(orderId);
    }

    @GetMapping("/external")
    @CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = "externalServiceFallback")
    public String callExternalService() {
        log.info("Calling External Service");
        return restTemplate.getForObject("http://localhost:8082/api", String.class);
    }

    public String externalServiceFallback(Exception e) {
        log.error("External Service failed, falling back", e);
        return "Fallback response: External Service is currently unavailable.";
    }
}
