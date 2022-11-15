package com.bridgelabz.bookstore.repository.order.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.bridgelabz.bookstore.repository.user.model.UserData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class OrderSummary {
    @Id
    @GeneratedValue
    private long orderId;  

    private LocalDateTime orderDate;

    private boolean cancelled;

    private String address;

    private float totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData userData;

    @OneToMany(mappedBy = "orderSummary")
    private List<OrderDetail> orderDetail;

}
