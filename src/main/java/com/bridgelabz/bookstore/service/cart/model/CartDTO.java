package com.bridgelabz.bookstore.service.cart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDTO {
    private long userId;
    private long bookID;
    private int quantity;
}
