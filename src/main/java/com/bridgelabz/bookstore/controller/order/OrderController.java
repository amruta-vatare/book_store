package com.bridgelabz.bookstore.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bridgelabz.bookstore.controller.order.model.CreateOrderRequest;
import com.bridgelabz.bookstore.controller.order.model.OrderResponse;
import com.bridgelabz.bookstore.service.order.IOrderService;
import com.bridgelabz.bookstore.service.order.model.OrderDTO;
import com.bridgelabz.bookstore.service.user.IUserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("order/")
public class OrderController {   
    @Autowired
    IOrderService service;

    @Autowired
    IUserService userService;

    @PostMapping("/add/{userToken}")
    public ResponseEntity<String> addOrder(@PathVariable String userToken, @RequestBody CreateOrderRequest createOrderRequest)
    {
       Long id  =  userService.getUserId(userToken);
       service.addOrder(id,createOrderRequest);
       return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(" \nNew Order was added successfully. (CODE 201)\n");
    }

    @GetMapping("/getOrderById/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        OrderDTO orderDto = service.getOrderById(orderId);
        OrderResponse response = RequestResponseMapper.fromService(orderDto); 
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(response);
    }
    @GetMapping("/getAll/{userToken}")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@PathVariable String userToken) {
        Long userID  =  userService.getUserId(userToken);
        List<OrderDTO> orderDtos = service.getAllOrders(userID);
        List<OrderResponse> responses = RequestResponseMapper.fromService(orderDtos); 
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(responses);
    }

    @DeleteMapping("remove/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
       service.deleteOrder(orderId);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Order was deleted successfully. (CODE 201)\n");
    }
    
}
