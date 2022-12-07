package com.bridgelabz.bookstore.controller.order.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrderRequest 
{
    List<BookOrderRequest> bookOrders;
    private String shippingAddress;
    private String shippingName; 
    private long shippingPhoneNo;
    private String shippingState;
    private long shippingZipCode;
    private String shippingCity;
    private String shippingType;
}
