package com.bridgelabz.bookstore.advice.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.bookstore.controller.user.model.UserResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class UserExceptionHandler {
    private static final String ERROR_MSG = "ERROR WHILE PROCESSING REST REQUEST";
     
    @ExceptionHandler(UserException.class)
    public ResponseEntity<UserResponse> handleEmployeePayrollException(UserException exception){
        UserResponse response = new UserResponse(ERROR_MSG,exception.getMessage());
        return new ResponseEntity<UserResponse>(response, HttpStatus.BAD_REQUEST);
    } 
}
