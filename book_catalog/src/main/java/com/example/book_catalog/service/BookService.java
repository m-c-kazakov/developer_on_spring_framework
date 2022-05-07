package com.example.book_catalog.service;

import com.example.book_catalog.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getALl();

    Book getById(Long booksId);

    Long create(String bookName);

    void update(Long bookId, String bookName);

    void remove(Long bookId);
}
