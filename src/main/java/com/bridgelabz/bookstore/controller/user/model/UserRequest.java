package com.bridgelabz.bookstore.controller.user.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    @NotBlank
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    private String firstName;
    @NotBlank
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    private String lastName;
    @NotBlank
    @Email(message = "Email is invalid")
    private String email;
    @NotBlank
    private String address;
    @NotNull
    private LocalDate dob;
    
    private String password;
}
