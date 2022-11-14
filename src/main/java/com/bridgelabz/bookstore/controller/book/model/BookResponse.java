package com.bridgelabz.bookstore.controller.book.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResponse {
    public BookResponse(String errorMsg, String message) {
    }
    private String bookName;
    private String autherName;
    private String bookDescription;
    private String bookImg  ;
    private float price;
    private int quantity;
}
