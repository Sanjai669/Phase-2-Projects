package com.paymentservice.paymentservice.vo;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    private Long orderId;
    private String productName;
    private Integer quantity;
    private Double price;
}
