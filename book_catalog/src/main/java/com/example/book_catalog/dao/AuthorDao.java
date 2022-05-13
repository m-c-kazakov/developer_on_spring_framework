package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;

import java.util.List;

public interface AuthorDao {

    long create(Author author);
    void update(Author author);
    void remove(Author author);
    Author get(Long authorId);
    List<Author> getAll();
}
