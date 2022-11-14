package com.bridgelabz.bookstore.controller.book;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.bookstore.controller.book.model.BookRequest;
import com.bridgelabz.bookstore.controller.book.model.BookResponse;
import com.bridgelabz.bookstore.service.book.model.BookDTO;

public class RequestResponseMapper {

    public static BookDTO toService(@Valid BookRequest bookRequest) {
        BookDTO dto = new BookDTO();
        dto.setBookName(bookRequest.getBookName());
        dto.setAutherName(bookRequest.getAutherName());
        dto.setBookDescription(bookRequest.getBookDescription());
        dto.setBookImg(bookRequest.getBookImg());
        dto.setPrice(bookRequest.getPrice());
        dto.setQuantity(bookRequest.getQuantity());
        return dto;
    }

    public static BookResponse fromService(BookDTO bookDTO) {
        BookResponse response = new BookResponse();
        response.setBookName(bookDTO.getBookName());
        response.setAutherName(bookDTO.getAutherName());
        response.setBookDescription(bookDTO.getBookDescription());
        response.setBookImg(bookDTO.getBookImg());
        response.setPrice(bookDTO.getPrice());
        response.setQuantity(bookDTO.getQuantity());
        return response;
    }

    public static List<BookResponse> fromService(List<BookDTO> bookDTOs) {
        List<BookResponse> responses = new ArrayList<>();
        for (BookDTO bookDTO : bookDTOs) {
            BookResponse response = new BookResponse();
            response.setBookName(bookDTO.getBookName());
            response.setAutherName(bookDTO.getAutherName());
            response.setBookDescription(bookDTO.getBookDescription());
            response.setBookImg(bookDTO.getBookImg());
            response.setPrice(bookDTO.getPrice());
            response.setQuantity(bookDTO.getQuantity());
            responses.add(response);
        }
        return responses;
    }
}
