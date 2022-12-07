package com.bridgelabz.bookstore.service.order.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrderDTO {
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
