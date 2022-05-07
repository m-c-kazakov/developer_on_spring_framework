package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Book;

import java.util.List;

public interface BookDao {
    long create(Book book);
    void update(Book book);
    void remove(Book book);
    Book get(Long bookId);
    List<Book> getAll();
}