package com.bridgelabz.bookstore.controller.user;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bridgelabz.bookstore.controller.user.model.UserRequest;
import com.bridgelabz.bookstore.service.user.IUserService;
import com.bridgelabz.bookstore.service.user.model.UserDTO;

@Controller
@RequestMapping("/bridgelabz")
public class UserController {
    @Autowired
    private IUserService service;

    @Autowired
    private Mapper mapper;

    @PostMapping("/add")
    public ResponseEntity<String> addContact(@RequestBody @Valid UserRequest userRequest){
        UserDTO dto =mapper.toService(userRequest);
        String token = service.addContact(dto);      
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(token+" \nNew contact was added successfully. (CODE 201)\n");
    }
}
