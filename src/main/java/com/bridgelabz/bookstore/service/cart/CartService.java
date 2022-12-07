package com.bridgelabz.bookstore.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.repository.book.IBookRepository;
import com.bridgelabz.bookstore.repository.cart.ICartRepository;
import com.bridgelabz.bookstore.repository.cart.model.CartData;
import com.bridgelabz.bookstore.repository.user.IUserRepository;
import com.bridgelabz.bookstore.security.user.JwtUtil;
import com.bridgelabz.bookstore.service.cart.model.CartDTO;

@Service
public class CartService implements ICartService {

    @Autowired
    ICartRepository repository;

    @Autowired
    IBookRepository bookRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    JwtUtil util;


    @Override
    public void addCart(CartDTO dto,String userIdToken) {
        CartData cart = new CartData();
        long userId = util.decodeToken(userIdToken);
        cart.setUserData(userRepository.findById(userId).get());
        cart.setBookData(bookRepository.findById(dto.getBookID()).get());
        cart.setQuantity(dto.getQuantity());

        List<CartData> result = repository.findCartItemsByUserIdAndBookId(userId, dto.getBookID());
        if(result == null || result.size() == 0)
        {
            repository.save(cart);
        }
    }

    @Override
    public CartDTO getCartById(Long id) {
    List<CartData> carts  = repository.findAll();
    CartDTO dto = DTOMapper.fromRepository(carts.stream().filter(cart->cart.getCartId() == id).findFirst().orElseThrow());
    return dto;
        
    }

    @Override
    public List<CartDTO> getAllCarts(String userIdToken) {
        long userId = util.decodeToken(userIdToken);
        List<CartData> cartDatas  = repository.findCartItemsByUserId(userId);
        List<CartDTO> cartDTOs = DTOMapper.fromRepository(cartDatas); 
        return cartDTOs;
    }

    @Override
    public void deleteCart(String userToken, Long book_id) {
        long user_id = util.decodeToken(userToken);
        repository.deleteCartItemByUserId(user_id, book_id);
        // CartData cart = repository.findById(bookId).get();
        // repository.delete(cart);
        
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
    public void updateQuantity(Long cartId, int quantity) {
        CartData cartData = repository.findById(cartId).get();
        cartData.setQuantity(quantity);
        repository.save(cartData);
    }
    
}
