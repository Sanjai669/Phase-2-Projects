package com.paymentservice.paymentservice.controller;

import com.paymentservice.paymentservice.entity.Payment;
import com.paymentservice.paymentservice.service.PaymentService;
import com.paymentservice.paymentservice.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public Payment savePayment(@RequestBody Payment payment) {
        log.info("Inside savePayment of PaymentController");
        return paymentService.savePayment(payment);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getPaymentWithOrder(@PathVariable("id") Long paymentId) {
        log.info("Inside getPaymentWithOrder of PaymentController");
        return paymentService.getPaymentWithOrder(paymentId);
    }
}
