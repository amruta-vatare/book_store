package com.bridgelabz.bookstore.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.repository.cart.model.CartData;

@Repository
public interface ICartRepository extends JpaRepository<CartData,Long>{
    
}
