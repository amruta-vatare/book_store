package com.bridgelabz.bookstore.controller.cart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartResponse {
    private long userId;
    private long bookID;
    private int quantity;
}
