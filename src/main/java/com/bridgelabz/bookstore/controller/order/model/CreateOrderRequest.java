package com.bridgelabz.bookstore.controller.order.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrderRequest 
{
    List<BookOrderRequest> bookOrders;
    String Address;
}
