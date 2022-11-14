package com.bridgelabz.bookstore.repository.cart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bridgelabz.bookstore.repository.book.model.BookData;
import com.bridgelabz.bookstore.repository.user.model.UserData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class CartData {
    @Id
    @GeneratedValue
    private long cartId;   

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData userData;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookData bookData;    

    private int quantity;
}
