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
import com.bridgelabz.bookstore.service.order.IOrderService;
import com.bridgelabz.bookstore.service.user.IUserService;

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
}
