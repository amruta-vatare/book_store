package com.bridgelabz.bookstore.service.cart;

import java.util.List;

import com.bridgelabz.bookstore.service.cart.model.CartDTO;

public interface ICartService {

    void addCart(CartDTO dto);

    CartDTO getCartById(Long id);

    List<CartDTO> getAllCarts();

    void deleteCart(Long id);

    void updateCartById(Long id, CartDTO cartDTO);

    void updateQuantity(Long id, int quantity);
    
}
