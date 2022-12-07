package com.bridgelabz.bookstore.service.book.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDTO {
    private long bookId;
    private String bookName;
    private String autherName;
    private String bookDescription;
    private String bookImg  ;
    private float price;
    private int quantity;
}
