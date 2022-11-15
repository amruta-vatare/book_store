package com.bridgelabz.bookstore.service.user;

import java.util.List;

import com.bridgelabz.bookstore.controller.user.model.LoginRequest;
import com.bridgelabz.bookstore.service.user.model.UserDTO;

public interface IUserService {

    String addUser(UserDTO dto);

    List<UserDTO> getAllUsers();

    UserDTO getUser(String token);

    UserDTO getUserByMail(String email);

    void updateUser(String mail,UserDTO userDTO);

    void deleteUser(String token);

    void forgotpassword(String email);

    void resetPassword(String token);

    boolean loginUser(LoginRequest loginRequest);

    void changePassword(String token, String newPassword);

    long getUserId(String token);
}
