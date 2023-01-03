package com.bridgelabz.bookstore.service.order;

import java.util.List;

import com.bridgelabz.bookstore.controller.order.model.CreateOrderRequest;
import com.bridgelabz.bookstore.service.order.model.OrderDTO;

public interface IOrderService {

    long addOrder(Long id, CreateOrderRequest createOrderRequest);

    OrderDTO getOrderById(Long orderId);

    List<OrderDTO> getAllOrders(Long userId);

    void deleteOrder(Long orderId);

    void updateStatusById(Long orderId);

    void getStatusById(Long orderId);
    
}
