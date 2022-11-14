package com.bridgelabz.bookstore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import com.bridgelabz.bookstore.controller.user.model.UserRequest;
import com.bridgelabz.bookstore.controller.user.model.UserResponse;
import com.bridgelabz.bookstore.repository.book.model.BookData;
import com.bridgelabz.bookstore.service.book.model.BookDTO;
import com.bridgelabz.bookstore.service.user.model.UserDTO;

@Component
public class RequestResponseMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toService(UserRequest userRequest) {
        UserDTO dto = new UserDTO();
        dto = this.modelMapper.map(userRequest, UserDTO.class);
        return dto;
    }

    public static List<UserResponse> fromService(List<UserDTO> userDtos) {
        List<UserResponse> userResponse = new ArrayList<>();
        for (UserDTO userDto : userDtos) {
            UserResponse response = new UserResponse();
            response.setFirstName(userDto.getFirstName());
            response.setLastName(userDto.getLastName());
            response.setAddress(userDto.getAddress());
            response.setDob(userDto.getDob());
            response.setEmail(userDto.getEmail());
            response.setPassword(userDto.getPassword());
            userResponse.add(response);
        }
        return userResponse;
    }

    public static UserResponse fromService(UserDTO userDto) {
        UserResponse response = new UserResponse();
        response.setFirstName(userDto.getFirstName());
        response.setLastName(userDto.getLastName());
        response.setPassword(userDto.getPassword());
        response.setAddress(userDto.getAddress());
        response.setDob(userDto.getDob());
        response.setEmail(userDto.getEmail());
        return response;
    }

    public static BookDTO fromRepository(BookData book) {
        return null;
    }
}
