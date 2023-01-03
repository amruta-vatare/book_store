package com.bridgelabz.bookstore.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bridgelabz.bookstore.controller.order.model.CreateOrderRequest;
import com.bridgelabz.bookstore.controller.order.model.OrderCreatedResponse;
import com.bridgelabz.bookstore.controller.order.model.OrderResponse;
import com.bridgelabz.bookstore.service.order.IOrderService;
import com.bridgelabz.bookstore.service.order.model.OrderDTO;
import com.bridgelabz.bookstore.service.user.IUserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("order/")
@CrossOrigin
public class OrderController {   
    @Autowired
    IOrderService service;

    @Autowired
    IUserService userService;

    @PostMapping("/add/{userToken}")
    public ResponseEntity<OrderCreatedResponse> addOrder(@PathVariable String userToken, @RequestBody CreateOrderRequest createOrderRequest)
    {
       Long id  =  userService.getUserId(userToken);
       long orderId = service.addOrder(id,createOrderRequest);
       OrderCreatedResponse orderCreatedResponse = new OrderCreatedResponse();
       orderCreatedResponse.setOrderId(orderId);
       orderCreatedResponse.setMessage("New Order was added successfully. (CODE 201)");
       return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderCreatedResponse);
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

    @PutMapping("cancelOrder/{orderId}")
    public ResponseEntity<String> CancelOrder(@PathVariable Long orderId){
        service.updateStatusById(orderId);
        return ResponseEntity
        .status(HttpStatus.OK)
        .body("Order status was updated successfully!");
    }

    @GetMapping("getStatusById/{orderId}")
    public ResponseEntity<String> getStatusById(@PathVariable Long orderId){
        service.getStatusById(orderId);
        return ResponseEntity
        .status(HttpStatus.OK)
        .body("Order status was updated successfully!");

    }
    
}
