package com.bridgelabz.bookstore.service.book;

import java.util.List;

import com.bridgelabz.bookstore.service.book.model.BookDTO;

public interface IBookService {

    String addBook(BookDTO dto);

    BookDTO getBook(Long id);

    List<BookDTO> getAllBooks();

    void deleteBook(Long id);

    void updateBook(Long id, BookDTO bookDTO);

    List<BookDTO> getBookByName(String book_name);
    
}
