package com.paymentservice.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.paymentservice.paymentservice.vo.Order;

@FeignClient(name = "order-service", url = "http://localhost:9001") // You can use the service name or URL
public interface OrderClient {

    @GetMapping("/orders/{orderId}")
    Order getOrderById(@PathVariable("orderId") Long orderId);
}
