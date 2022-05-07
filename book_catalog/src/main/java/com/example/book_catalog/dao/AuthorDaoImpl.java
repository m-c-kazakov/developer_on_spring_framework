package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
import com.example.book_catalog.domain.Book;

import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    @Override
    public long create(Author author) {
        return 0;
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void remove(Author author) {

    }

    @Override
    public Book get(Long authorId) {
        return null;
    }

    @Override
    public List<Author> getAll() {
        return null;
    }
}
