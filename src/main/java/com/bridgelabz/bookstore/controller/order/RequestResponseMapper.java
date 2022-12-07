package com.bridgelabz.bookstore.controller.order;

import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.bookstore.controller.order.model.OrderResponse;
import com.bridgelabz.bookstore.service.order.model.OrderDTO;

public class RequestResponseMapper {

    public static OrderResponse fromService(OrderDTO orderDto) {
        OrderResponse response = new OrderResponse();
        response.setUserId(orderDto.getUserId());
        response.setOrderDate(orderDto.getOrderDate());
        response.setTotalPrice(orderDto.getTotalPrice());
        response.setBooksInOrder(orderDto.getBooksInOrder());
        response.setShippingAddress(orderDto.getShippingAddress());
        response.setShippingName(orderDto.getShippingName());
        response.setShippingPhoneNo(orderDto.getShippingPhoneNo());
        response.setShippingState(orderDto.getShippingState());
        response.setShippingCity(orderDto.getShippingCity());
        response.setShippingZipCode(orderDto.getShippingZipCode());
        response.setShippingType(orderDto.getShippingType());
        return response;
    }

    public static List<OrderResponse> fromService(List<OrderDTO> orderDtos) {
        List<OrderResponse> responses = new ArrayList<>();
        for (OrderDTO orderDTO : orderDtos) {
            responses.add(fromService(orderDTO));
        }
        return responses;
    }
    
}
