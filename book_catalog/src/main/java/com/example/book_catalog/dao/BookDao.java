package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    long create(Book book);
    void update(Book book);
    void remove(Book book);
    Optional<Book> get(Long bookId);
    List<Book> getAll();
}
