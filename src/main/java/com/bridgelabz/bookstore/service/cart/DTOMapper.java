package com.bridgelabz.bookstore.service.cart;

import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.bookstore.repository.cart.model.CartData;
import com.bridgelabz.bookstore.service.cart.model.CartDTO;

public class DTOMapper {
    public static CartDTO fromRepository(CartData cart) {
        CartDTO dto = new CartDTO();
        dto.setBookID(cart.getBookData().getBookId());
        dto.setUserId(cart.getUserData().getUser_id());
        dto.setQuantity(cart.getQuantity());
        return dto;
    }

    public static List<CartDTO> fromRepository(List<CartData> cartDatas) {
        List<CartDTO> dtos = new ArrayList<>();
        for (CartData cartData : cartDatas) {
            dtos.add(fromRepository(cartData));
        }
        return dtos;
    }
    
}
