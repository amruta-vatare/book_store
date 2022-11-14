package com.bridgelabz.bookstore.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.repository.book.IBookRepository;
import com.bridgelabz.bookstore.repository.cart.ICartRepository;
import com.bridgelabz.bookstore.repository.cart.model.CartData;
import com.bridgelabz.bookstore.repository.user.IUserRepository;
import com.bridgelabz.bookstore.service.cart.model.CartDTO;

@Service
public class CartService implements ICartService {

    @Autowired
    ICartRepository repository;

    @Autowired
    IBookRepository bookRepository;

    @Autowired
    IUserRepository userRepository;

    @Override
    public void addCart(CartDTO dto) {
        CartData cart = new CartData();
        cart.setUserData(userRepository.findById(dto.getUserId()).get());
        cart.setBookData(bookRepository.findById(dto.getBookID()).get());
        cart.setQuantity(dto.getQuantity());
        repository.save(cart);
    }

    @Override
    public CartDTO getCartById(Long id) {
    List<CartData> carts  = repository.findAll();
    CartDTO dto = DTOMapper.fromRepository(carts.stream().filter(cart->cart.getCartId() == id).findFirst().orElseThrow());
    return dto;
        
    }

    @Override
    public List<CartDTO> getAllCarts() {
        List<CartData> cartDatas  = repository.findAll();
        List<CartDTO> cartDTOs = DTOMapper.fromRepository(cartDatas);
        return cartDTOs;
    }

    @Override
    public void deleteCart(Long id) {
        CartData cart = repository.findById(id).get();
        repository.delete(cart);
        
    }

    @Override
    public void updateCartById(Long id, CartDTO cartDTO) {
        CartData cartData = repository.findById(id).get();
        cartData.setBookData(bookRepository.findById(cartDTO.getBookID()).get());
        cartData.setUserData(userRepository.findById(cartDTO.getUserId()).get());
        cartData.setQuantity(cartDTO.getQuantity());
        repository.save(cartData);
    }

    @Override
    public void updateQuantity(Long id, int quantity) {
        CartData cartData = repository.findById(id).get();
        cartData.setQuantity(quantity);
        repository.save(cartData);
    }
    
}
