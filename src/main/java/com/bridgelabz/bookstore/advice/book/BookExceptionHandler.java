package com.bridgelabz.bookstore.advice.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.bookstore.controller.book.model.BookResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class BookExceptionHandler {
    private static final String ERROR_MSG = "ERROR WHILE PROCESSING REST REQUEST";
     
    @ExceptionHandler(BookException.class)
    public ResponseEntity<BookResponse> handleEmployeePayrollException(BookException exception){
        BookResponse response = new BookResponse(ERROR_MSG,exception.getMessage());
        return new ResponseEntity<BookResponse>(response, HttpStatus.BAD_REQUEST);
    } 
}
