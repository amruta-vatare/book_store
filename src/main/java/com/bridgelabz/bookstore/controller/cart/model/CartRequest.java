package com.bridgelabz.bookstore.controller.cart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartRequest {
    private long bookID;
    private int quantity;
}
