package com.bridgelabz.bookstore.repository.book;

import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.repository.book.model.BookData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface IBookRepository extends JpaRepository<BookData,Long>{
    @Query(value = "select * from book_data book where book.book_Name LIKE %:bookName%",nativeQuery = true)
    List<BookData> findBookByName(@Param("bookName") String bookName);
}
