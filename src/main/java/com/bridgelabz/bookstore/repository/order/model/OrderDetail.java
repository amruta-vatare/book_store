package com.bridgelabz.bookstore.repository.order.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bridgelabz.bookstore.repository.book.model.BookData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class OrderDetail {
    @Id
    @GeneratedValue
    private long orderDetailId;

    private int quantity;

    private float price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderSummary orderSummary;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookData bookData;
}
