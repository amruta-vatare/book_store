package com.bridgelabz.bookstore.controller.cart;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bridgelabz.bookstore.controller.cart.model.CartRequest;
import com.bridgelabz.bookstore.controller.cart.model.CartResponse;
import com.bridgelabz.bookstore.service.cart.ICartService;
import com.bridgelabz.bookstore.service.cart.model.CartDTO;

@Controller
@RequestMapping("cart/")
public class CartController {
    //insert,getall,getbyID,delete,updatebyID,updateQuantity
    @Autowired
    ICartService service;

    @PostMapping("/add")
    public ResponseEntity<String> addCart(@RequestBody CartRequest cartRequest){
        CartDTO dto = RequestResponseMapper.toService(cartRequest);
        service.addCart(dto);      
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(" \nNew cart was added successfully. (CODE 201)\n");
    }
    
    
    @GetMapping("get/{id}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable Long id) {
        CartDTO cartDTO =  service.getCartById(id);
        CartResponse cartResponse = RequestResponseMapper.fromService(cartDTO);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartResponse>> getAllCarts() {
        List<CartDTO> cartDTOs =  service.getAllCarts();
        List<CartResponse> cartResponses =  RequestResponseMapper.fromService(cartDTOs);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartResponses);
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
       service.deleteCart(id);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Cart was deleted successfully. (CODE 201)\n");
    }
    
    @PutMapping("updateCart/{id}")
    public ResponseEntity<String> updateCartById(@PathVariable Long id,@RequestBody CartRequest cartRequest) {
        CartDTO cartDTO = RequestResponseMapper.toService(cartRequest);
        service.updateCartById(id,cartDTO);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Cart was updated successfully. (CODE 201)\n");
    }

    @PutMapping("updateQuantity/{id}")
    public ResponseEntity<String> updateQuantity(@PathVariable Long id,@RequestBody Map<String,Object> body) {
       int quantity = Integer.parseInt(body.get("quantity").toString());
        service.updateQuantity(id,quantity);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Cart was updated successfully. (CODE 201)\n");
    }

}
