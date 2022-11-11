package com.bridgelabz.bookstore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.bridgelabz.bookstore.controller.user.model.UserRequest;
import com.bridgelabz.bookstore.service.user.model.UserDTO;
@Component
public class Mapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toService(UserRequest userRequest) {
        UserDTO dto = new UserDTO();
        dto = this.modelMapper.map(userRequest, UserDTO.class);
        return dto;
    }
}
