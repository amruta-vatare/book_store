package com.bridgelabz.bookstore.controller.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderCreatedResponse {
    private long orderId;
    private String Message;
}
