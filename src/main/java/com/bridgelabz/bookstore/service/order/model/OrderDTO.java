package com.bridgelabz.bookstore.service.order.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
    private List<BookOrderDTO> booksInOrder;
    private String address;
    private LocalDateTime orderDate;
    private long userId;
    private boolean cancel;
    private float totalPrice;
}
