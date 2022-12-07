package com.bridgelabz.bookstore.controller.user;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bridgelabz.bookstore.controller.user.model.LoginRequest;
import com.bridgelabz.bookstore.controller.user.model.UserRequest;
import com.bridgelabz.bookstore.controller.user.model.UserResponse;
import com.bridgelabz.bookstore.service.user.IUserService;
import com.bridgelabz.bookstore.service.user.model.UserDTO;

@Controller
@RequestMapping("/bridgelabz")
@CrossOrigin()
public class UserController {
    @Autowired
    private IUserService service;

    @Autowired
    private RequestResponseMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody @Valid UserRequest userRequest){
        UserDTO dto =mapper.toService(userRequest);
        String token = service.addUser(dto);      
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(token+" \nNew contact was added successfully. (CODE 201)\n");
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserDTO> userDto =  service.getAllUsers();
        List<UserResponse> userResponse =  RequestResponseMapper.fromService(userDto);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userResponse);
    }

     
    @GetMapping("get/{token}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String token) {
        UserDTO userDto =  service.getUser(token);
        UserResponse userResponse =   RequestResponseMapper.fromService(userDto);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userResponse);
    }

    @GetMapping("getByMail/{mail}")
    public ResponseEntity<UserResponse> getUserByMail(@PathVariable String mail) {
        UserDTO userDto =  service.getUserByMail(mail);
        UserResponse userResponse =   RequestResponseMapper.fromService(userDto);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userResponse);
    }

    @PutMapping("update/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email,@RequestBody UserRequest userRequest) {
        UserDTO userDto = mapper.toService(userRequest);
        service.updateUser(email,userDto);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("User was updated successfully. (CODE 201)\n");
    }

    @DeleteMapping("remove/{token}")
    public ResponseEntity<String> deleteUser(@PathVariable String token) {
       service.deleteUser(token);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("User was deleted successfully. (CODE 201)\n");
    }

    @PostMapping("forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String,Object> body){
        String emailValue = body.get("email").toString();
        service.forgotpassword(emailValue);
        return ResponseEntity.status(HttpStatus.OK).body("Reset link has been sent on mail");
    }

   
    @GetMapping("resetPassword/{token}")
    public ResponseEntity<String> resetPassword(@PathVariable String token) {
        service.resetPassword(token);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Your password has been successfully updated. (CODE 201)\n");
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){
        String token = service.loginUser(loginRequest);
        if(token != null){
            return ResponseEntity
            .status(HttpStatus.OK)
            .body(token);
        }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Unable to login! (CODE 201)\n");

    }

    @PutMapping("changePassword/{token}")
    public ResponseEntity<String> changePassword(@PathVariable String token,@RequestBody Map<String,Object> body) {
        service.changePassword(token,body.get("newPassword").toString());
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Your password has been successfully updated. (CODE 201)\n");
    }



}
