package com.bridgelabz.bookstore.controller.cart;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class CartController {
    //insert,getall,getbyID,delete,updatebyID,updateQuantity
    @Autowired
    ICartService service;

    @PostMapping("/add/{userIdToken}")
    public ResponseEntity<String> addCart(@RequestBody CartRequest cartRequest,@PathVariable String userIdToken){
        CartDTO dto = RequestResponseMapper.toService(cartRequest);
        service.addCart(dto,userIdToken);      
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(" \nNew cart was added successfully. (CODE 201)\n");
    }
    
    
    @GetMapping("get/{cartId}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable Long cartId) {
        CartDTO cartDTO =  service.getCartById(cartId);
        CartResponse cartResponse = RequestResponseMapper.fromService(cartDTO);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartResponse);
    }

    @GetMapping("/all/{userIdToken}")
    public ResponseEntity<List<CartResponse>> getAllCarts(@PathVariable String userIdToken) {
        List<CartDTO> cartDTOs =  service.getAllCarts(userIdToken);
        List<CartResponse> cartResponses =  RequestResponseMapper.fromService(cartDTOs);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartResponses);
    }

    @DeleteMapping("remove/{userToken}")
    public ResponseEntity<String> deleteCart(@RequestBody CartRequest cartRequest, @PathVariable String userToken) {
        Long bookId = cartRequest.getBookID();
        service.deleteCart(userToken,bookId);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Cart was deleted successfully. (CODE 201)\n");
    }
    
    @PutMapping("updateCart/{cartId}")
    public ResponseEntity<String> updateCartById(@PathVariable Long cartId,@RequestBody CartRequest cartRequest) {
        CartDTO cartDTO = RequestResponseMapper.toService(cartRequest);
        service.updateCartById(cartId,cartDTO);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Cart was updated successfully. (CODE 201)\n");
    }

    @PutMapping("updateQuantity/{cartId}")
    public ResponseEntity<String> updateQuantity(@PathVariable Long cartId,@RequestBody Map<String,Object> body) {
       int quantity = Integer.parseInt(body.get("quantity").toString());
        service.updateQuantity(cartId,quantity);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Cart was updated successfully. (CODE 201)\n");
    }

}
