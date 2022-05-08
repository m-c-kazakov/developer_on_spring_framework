package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    long create(Genre genre);
    void update(Genre genre);
    void remove(Genre genre);
    Optional<Genre> get(Long genreId);
    List<Genre> getAll();
}
