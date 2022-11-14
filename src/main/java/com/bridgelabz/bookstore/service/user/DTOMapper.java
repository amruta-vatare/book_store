package com.bridgelabz.bookstore.service.user;

import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.bookstore.repository.user.model.UserData;
import com.bridgelabz.bookstore.service.user.model.UserDTO;

public class DTOMapper {

    public static UserData toRepository(UserDTO dto) {
        UserData data = new UserData();
        data.setFirstName(dto.getFirstName());
        data.setLastName(dto.getLastName());
        data.setPassword(dto.getPassword());
        data.setAddress(dto.getAddress());
        data.setDob(dto.getDob());
        data.setEmail(dto.getEmail());
        return data;
    }

    public static List<UserDTO> listFromRepository(List<UserData> datas) {
        List<UserDTO> users = new ArrayList<>();
        for (UserData user : datas) {
            UserDTO dto = new UserDTO();
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setPassword(user.getPassword());
            dto.setDob(user.getDob());
            dto.setEmail(user.getEmail());
            dto.setAddress(user.getAddress());
            users.add(dto);
        }
        return users;
    }

    public static UserDTO fromRepository(UserData data) {
        UserDTO dto = new UserDTO();
        dto.setFirstName(data.getFirstName());
            dto.setLastName(data.getLastName());
            dto.setPassword(data.getPassword());
            dto.setDob(data.getDob());
            dto.setEmail(data.getEmail());
            dto.setAddress(data.getAddress());
        return dto;
    }

}
