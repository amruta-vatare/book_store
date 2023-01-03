package com.bridgelabz.bookstore.service.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookOrderDTO {
    private long bookId;
    private String bookName;
    private String bookImg;
    private String autherName;
    private int quantity;
    private float price;
}
