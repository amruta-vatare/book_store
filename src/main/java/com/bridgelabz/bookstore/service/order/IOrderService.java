package com.bridgelabz.bookstore.service.order;

import com.bridgelabz.bookstore.controller.order.model.CreateOrderRequest;

public interface IOrderService {

    void addOrder(Long id, CreateOrderRequest createOrderRequest);
    
}
