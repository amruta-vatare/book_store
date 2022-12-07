package com.bridgelabz.bookstore.repository.cart;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.repository.cart.model.CartData;

@Repository
public interface ICartRepository extends JpaRepository<CartData,Long>{
    @Query(value = "select * from cart_data where user_id = :user_id",nativeQuery = true)
    List<CartData> findCartItemsByUserId(@Param("user_id") Long user_id);

    @Query(value = "select * from cart_data c where c.user_id = :user_id and c.book_id = :book_id",nativeQuery = true)
    List<CartData> findCartItemsByUserIdAndBookId(@Param("user_id") Long user_id, @Param("book_id") Long book_id);

    @Transactional
    @Modifying
    @Query(value = "delete from cart_data c where c.user_id = :user_id and c.book_id = :book_id" ,nativeQuery = true)
    void deleteCartItemByUserId(@Param("user_id") Long user_id, @Param("book_id") Long book_id);

    @Transactional
    @Modifying
    @Query(value = "delete from cart_data c where c.user_id = :user_id", nativeQuery = true)
    void clearCartItemsOfUser(@Param("user_id") Long user_id);
}
