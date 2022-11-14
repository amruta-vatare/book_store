package com.bridgelabz.bookstore.service.user;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.advice.user.UserException;
import com.bridgelabz.bookstore.controller.user.model.LoginRequest;
import com.bridgelabz.bookstore.repository.user.IUserRepository;
import com.bridgelabz.bookstore.repository.user.model.UserData;
import com.bridgelabz.bookstore.security.user.EmailSenderService;
import com.bridgelabz.bookstore.security.user.JwtUtil;
import com.bridgelabz.bookstore.service.user.model.LoginDTO;
//import com.bridgelabz.bookstore.service.user.model.LoginDTO;
import com.bridgelabz.bookstore.service.user.model.UserDTO;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepository repository;
    
    @Autowired
    EmailSenderService senderService;

    @Autowired
    JwtUtil util;

    @Override
    public String addUser(UserDTO dto) {
        UserData user = DTOMapper.toRepository(dto);
        repository.save(user);
        String token = util.generateToken(user.getUser_id());
        try{
            senderService.sendEmail(dto.getEmail(), "Token authentication", "Hello, "+user.getFirstName()+" "+user.getLastName()+"\nSuccessfully registered! \n"+token);
        }catch(Exception e){
            e.printStackTrace();
        }
        return token;
    }
   
    
    @Override
    public List<UserDTO> getAllUsers() {
        List<UserData> datas = repository.findAll();
        return DTOMapper.listFromRepository(datas);
    }

    @Override
    public UserDTO getUser(String token) {
        int id = util.decodeToken(token);
        List<UserData> users  = repository.findAll();
        UserData data = users.stream().filter(user->user.getUser_id() == id).findFirst().orElseThrow(()->new UserException("User is not found!"));
        return DTOMapper.fromRepository(data);
    }

    
    
    @Override
    public UserDTO getUserByMail(String mail) {
        UserData user =  repository.findUserByEmail(mail);
        if(user == null){
            throw new UserException("User is not present with this mail_id!");
        }
        return DTOMapper.fromRepository(user);
    }
    
    @Override
    public void updateUser(String email, UserDTO usertDto) {
     UserData user = repository.findUserByEmail(email);
     user.setFirstName(usertDto.getFirstName());
     user.setLastName(usertDto.getLastName());
     user.setAddress(usertDto.getAddress());
     user.setEmail(usertDto.getEmail());
     user.setDob(usertDto.getDob());
     user.setPassword(usertDto.getPassword());
    repository.save(user);
    senderService.sendEmail(user.getEmail(), "Update Request", "Hello, "+user.getFirstName()+" "+user.getLastName()+"\n"+email+"\n the contact was updated successfully!");
    }


    @Override
    public void deleteUser(String token) {
        long id = util.decodeToken(token);
        UserData user = repository.findById(id).get();
        repository.delete(user);
        senderService.sendEmail(user.getEmail(), "Confirmation authentication", "Hello, "+user.getFirstName()+" "+user.getLastName()+"\n"+token+"\n the contact was deleted successfully!");
        
    }

    @Override
    public void forgotpassword(String email) {
        UserData user = repository.findUserByEmail(email);
        String token = util.generateToken(user.getUser_id());
        try{
            senderService.sendEmail(user.getEmail(), "Reset Password", "Hello, "+user.getFirstName()+" "+user.getLastName()+"\nYou have requested to reset your password.\n"
        + "Click the link below to change your password:"+"\n http://localhost:8080/bridgelabz/resetPassword/"+token);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }


    @Override
    public void resetPassword(String tokenString) {
        long id = util.decodeToken(tokenString);
        UserData user = repository.findById(id).get();
        int int_random = ThreadLocalRandom.current().nextInt(); 
        String pass = int_random+"@"+user.getLastName();
        user.setPassword(pass);
        repository.save(user);
        try{
            senderService.sendEmail(user.getEmail(), "Reset Password", "Hello, "+user.getFirstName()+" "+user.getLastName()+"\nYou have requested to reset your password.\n"
        + "Your reset password is :"+"\n "+pass);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean loginUser(LoginRequest loginRequest) {
        UserData user = repository.findUserByEmail(loginRequest.getUsername());
        if(user.getPassword() != loginRequest.getPassword()){
              throw new UserException("Invalide user...Login fail");
        }
        else{
            return true;
        }   
    }


    @Override
    public void changePassword(String token, String newPassword) {
        long id = util.decodeToken(token);
        UserData user = repository.findById(id).get();
        if(user != null){
            user.setPassword(newPassword);
            repository.save(user);
        }else{
            throw new UserException("Unable to update the password!");
        }
        
    }

}
