package com.bridgelabz.bookstore.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.advice.book.BookException;
import com.bridgelabz.bookstore.repository.book.IBookRepository;
import com.bridgelabz.bookstore.repository.book.model.BookData;
import com.bridgelabz.bookstore.service.book.model.BookDTO;

@Service
public class BookService implements IBookService {
    @Autowired
    IBookRepository repository;

    @Override
    public String addBook(BookDTO dto) {
        BookData book = DTOMapper.toRepository(dto);
        repository.save(book);
        return "Book is successfully added!";
    }

    @Override
    public BookDTO getBook(Long id) {
    List<BookData> books  = repository.findAll();
    BookDTO dto = DTOMapper.fromRepository(books.stream().filter(book->book.getBookId() == id).findFirst().orElseThrow(()->new BookException("Book is not found with this id")));
    return dto;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookData> books  = repository.findAll();
        List<BookDTO> bookDTOs = DTOMapper.fromRepository(books);
        return bookDTOs;
    }

    @Override
    public void deleteBook(Long id) {
        BookData book = repository.findById(id).get();
        repository.delete(book);
        
    }

    @Override
    public void updateBook(Long id, BookDTO bookDTO) {
        BookData bookData = repository.findById(id).get();
        bookData.setBookName(bookDTO.getBookName());
        bookData.setAutherName(bookDTO.getAutherName());
        bookData.setBookDescription(bookDTO.getBookDescription());
        bookData.setBookImg(bookDTO.getBookImg());
        bookData.setPrice(bookDTO.getPrice());
        bookData.setQuantity(bookDTO.getQuantity());
        repository.save(bookData);
    }

    @Override
    public List<BookDTO> getBookByName(String book_name) {
      List<BookData> books = repository.findBookByName(book_name);
      List<BookDTO> dtos = DTOMapper.fromRepository(books);
      return dtos;
    }
    
}
