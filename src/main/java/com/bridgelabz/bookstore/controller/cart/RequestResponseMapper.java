package com.bridgelabz.bookstore.controller.cart;

import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.bookstore.controller.cart.model.CartRequest;
import com.bridgelabz.bookstore.controller.cart.model.CartResponse;
import com.bridgelabz.bookstore.service.cart.model.CartDTO;

public class RequestResponseMapper {

    public static CartDTO toService(CartRequest cartRequest) {
        CartDTO dto = new CartDTO();
        dto.setUserId(cartRequest.getUserId());
        dto.setBookID(cartRequest.getBookID());
        dto.setQuantity(cartRequest.getQuantity());
        return dto;
    }

    public static CartResponse fromService(CartDTO cartDTO) {
        CartResponse response = new CartResponse();
        response.setUserId(cartDTO.getUserId());
        response.setBookID(cartDTO.getBookID());
        response.setQuantity(cartDTO.getQuantity());
        return response;
    }

    public static List<CartResponse> fromService(List<CartDTO> cartDTOs) {
        List<CartResponse> responses = new ArrayList<>();
        for (CartDTO cartDTO : cartDTOs) {
            responses.add(fromService(cartDTO));
        }
        return responses;
    }
    
}
