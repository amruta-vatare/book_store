package com.bridgelabz.bookstore.repository.book.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bridgelabz.bookstore.repository.cart.model.CartData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class BookData {
    @Id
    @GeneratedValue
    private long bookId;
    private String bookName;
    private String autherName;
    private String bookDescription;
    private String bookImg  ;
    private float price;
    private int quantity;

    @OneToMany(mappedBy = "bookData")
    private List<CartData> cartItems;
}
