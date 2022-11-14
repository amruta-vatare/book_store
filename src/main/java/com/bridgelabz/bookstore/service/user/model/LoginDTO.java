package com.bridgelabz.bookstore.service.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
    String username;
    String password;
}
