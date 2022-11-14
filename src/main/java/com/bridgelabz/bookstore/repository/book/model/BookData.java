package com.bridgelabz.bookstore.repository.book.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
