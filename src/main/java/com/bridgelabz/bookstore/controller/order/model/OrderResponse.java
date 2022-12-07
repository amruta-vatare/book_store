package com.bridgelabz.bookstore.controller.order.model;

import java.time.LocalDateTime;
import java.util.List;

import com.bridgelabz.bookstore.service.order.model.BookOrderDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderResponse {
    private List<BookOrderDTO> booksInOrder;
    private LocalDateTime orderDate;
    private long userId;
    private boolean cancel;
    private float totalPrice;
    private String shippingAddress;
    private String shippingName; 
    private long shippingPhoneNo;
    private String shippingState;
    private long shippingZipCode;
    private String shippingCity;
    private String shippingType;
}
