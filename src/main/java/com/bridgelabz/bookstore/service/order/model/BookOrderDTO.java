package com.bridgelabz.bookstore.service.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookOrderDTO {
    private long bookId;
    private int quantity;
    private float price;
}
