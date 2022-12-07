package com.bridgelabz.bookstore.controller.book;

import java.util.List;

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

import com.bridgelabz.bookstore.controller.book.model.BookRequest;
import com.bridgelabz.bookstore.controller.book.model.BookResponse;
import com.bridgelabz.bookstore.service.book.IBookService;
import com.bridgelabz.bookstore.service.book.model.BookDTO;

@Controller
@RequestMapping("/book")
@CrossOrigin
public class BookController {
    @Autowired
    IBookService service;
    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody @Valid BookRequest bookRequest){
        BookDTO dto = RequestResponseMapper.toService(bookRequest);
        String token = service.addBook(dto);      
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(token+" \n New book was added successfully. (CODE 201)\n");
    }
    
    
    @GetMapping("get/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        BookDTO bookDTO =  service.getBook(id);
        BookResponse bookResponse = RequestResponseMapper.fromService(bookDTO);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(bookResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookDTO> bookDTO =  service.getAllBooks();
        List<BookResponse> bookResponses =  RequestResponseMapper.fromService(bookDTO);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(bookResponses);
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
       service.deleteBook(id);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Book was deleted successfully. (CODE 201)\n");
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id,@RequestBody BookRequest bookRequest) {
        BookDTO bookDTO = RequestResponseMapper.toService(bookRequest);
        service.updateBook(id,bookDTO);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Book was updated successfully. (CODE 201)\n");
    }

    @GetMapping("bookName/{book_name}")
    public ResponseEntity<List<BookResponse>> getBookByName(@PathVariable String book_name) {
        List<BookDTO> bookDTOs =  service.getBookByName(book_name);
        List<BookResponse> bookResponse =   RequestResponseMapper.fromService(bookDTOs);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(bookResponse);
    }
}
