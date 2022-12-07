package com.bridgelabz.bookstore.repository.order.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    private float totalPrice;

    private String shippingAddress;
    
    private String shippingName; 

    private long shippingPhoneNo;

    private String shippingState;

    private long shippingZipCode;

    private String shippingCity;

    private String shippingType;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData userData;

    @OneToMany(mappedBy = "orderSummary", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderDetail> orderDetail;

}
