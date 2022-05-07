package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
import com.example.book_catalog.domain.Book;

import java.util.List;

public interface AuthorDao {

    long create(Author author);
    void update(Author author);
    void remove(Author author);
    Book get(Long authorId);
    List<Author> getAll();
}
