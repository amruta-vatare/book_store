package com.bridgelabz.bookstore.controller.user.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserResponse {
    public UserResponse(String errorMsg, String message) {
    }
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate dob;
    private String password;
}
