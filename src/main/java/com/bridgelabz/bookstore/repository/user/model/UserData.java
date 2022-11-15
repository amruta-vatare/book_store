package com.bridgelabz.bookstore.repository.user.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bridgelabz.bookstore.repository.cart.model.CartData;
import com.bridgelabz.bookstore.repository.order.model.OrderSummary;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue
    private long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate dob;
    private String password;

    @OneToMany(mappedBy = "userData")
    private List<CartData> cartItems;

    @OneToMany(mappedBy = "userData")
    private List<OrderSummary> orders;
}