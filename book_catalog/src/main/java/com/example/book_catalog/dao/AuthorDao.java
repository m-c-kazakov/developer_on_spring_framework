package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    long create(Author author);
    void update(Author author);
    void remove(Author author);
    Optional<Author> get(Long authorId);
    List<Author> getAll();
}
