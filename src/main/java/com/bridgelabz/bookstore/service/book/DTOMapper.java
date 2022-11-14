package com.bridgelabz.bookstore.service.book;

import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.bookstore.repository.book.model.BookData;
import com.bridgelabz.bookstore.service.book.model.BookDTO;

public class DTOMapper {

    public static BookData toRepository(BookDTO dto) {
        BookData data = new BookData();
        data.setBookName(dto.getBookName());
        data.setAutherName(dto.getAutherName());
        data.setBookDescription(dto.getBookDescription());
        data.setBookImg(dto.getBookImg());
        data.setPrice(dto.getPrice());
        data.setQuantity(dto.getQuantity());
        return data;
    }

    public static BookDTO fromRepository(BookData book) {
        BookDTO dto = new BookDTO();
        dto.setBookName(book.getBookName());
        dto.setAutherName(book.getAutherName());
        dto.setBookDescription(book.getBookDescription());
        dto.setBookImg(book.getBookImg());
        dto.setPrice(book.getPrice());
        dto.setQuantity(book.getQuantity());
        return dto;
    }

    public static List<BookDTO> fromRepository(List<BookData> books) {
        List<BookDTO> dtos = new ArrayList<>();
        for (BookData book : books) {
            dtos.add(fromRepository(book));
        }
        return dtos;
    }
    
}
