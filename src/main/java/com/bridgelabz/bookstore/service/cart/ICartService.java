package com.bridgelabz.bookstore.service.cart;

import java.util.List;

import com.bridgelabz.bookstore.service.cart.model.CartDTO;

public interface ICartService {

    void addCart(CartDTO dto,String userIdToken);

    CartDTO getCartById(Long id);

    List<CartDTO> getAllCarts(String userIdToken);

    void deleteCart(String userToken, Long BookId);

    void updateCartById(Long cartId, CartDTO cartDTO);

    void updateQuantity(Long cartId, int quantity);
    
}
