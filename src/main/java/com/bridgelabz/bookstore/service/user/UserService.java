package com.bridgelabz.bookstore.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.repository.user.IUserRepository;
import com.bridgelabz.bookstore.repository.user.model.UserData;
import com.bridgelabz.bookstore.security.user.EmailSenderService;
import com.bridgelabz.bookstore.security.user.JwtUtil;
import com.bridgelabz.bookstore.service.user.model.UserDTO;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepository repository;
    
    @Autowired
    EmailSenderService senderService;

    @Autowired
    JwtUtil util;
      
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addContact(UserDTO dto) {
        UserData user = toRepository(dto);
        repository.save(user);
        String token = util.generateToken(user.getUser_id());
        try{
            senderService.sendEmail(dto.getEmail(), "Token authentication", "Hello, "+user.getFirstName()+" "+user.getLastName()+"\nSuccessfully registered! \n"+token);
        }catch(Exception e){
            e.printStackTrace();
        }
        return token;
    }
    
    public UserData toRepository(UserDTO dto) {
        UserData user = new UserData();
        user  = modelMapper.map(dto,UserData.class);
        return user;
    }
    
}
