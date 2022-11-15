package com.bridgelabz.bookstore.controller.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookOrderRequest {
    public long bookId;
    public int quantity;
}
