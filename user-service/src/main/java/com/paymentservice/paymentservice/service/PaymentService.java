package com.paymentservice.paymentservice.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.paymentservice.paymentservice.client.OrderClient;
import com.paymentservice.paymentservice.entity.Payment;
import com.paymentservice.paymentservice.repository.PaymentRepository;
import com.paymentservice.paymentservice.vo.Order;
import com.paymentservice.paymentservice.vo.ResponseTemplateVO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

    private static final String ORDER_SERVICE = "orderService";

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderClient orderClient;

    public Payment savePayment(Payment payment) {
        log.info("Inside savePayment of PaymentService");
        return paymentRepository.save(payment);
    }
   

    @CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = "orderServiceFallback")
    public ResponseTemplateVO getPaymentWithOrder(Long paymentId) {
        log.info("Inside getPaymentWithOrder of PaymentService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Payment payment = paymentRepository.findByPaymentId(paymentId);

        Order order = orderClient.getOrderById(payment.getOrderId());
        vo.setPayment(payment);
        vo.setOrder(order);

        return vo;
    }

    // Fallback method for Circuit Breaker
    public ResponseTemplateVO orderServiceFallback(Long paymentId, Exception e) {
        log.error("Order Service is down, returning fallback response.", e);
        
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Payment payment = paymentRepository.findByPaymentId(paymentId);
        
        // Set the error message between "payment" and "order"
        vo.setErrorMessage("Payment Failed: Unable to process order. Please try again later.");

        // Initialize Order with default values
        Order fallbackOrder = new Order(0L, "No Order", 0, 0.0);

        vo.setPayment(payment);
        vo.setOrder(fallbackOrder);

        return vo;
    }


    }

