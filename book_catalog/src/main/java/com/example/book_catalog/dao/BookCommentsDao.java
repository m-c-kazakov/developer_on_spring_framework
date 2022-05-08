package com.example.book_catalog.dao;

import com.example.book_catalog.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentsDao {

    long create(BookComment bookComment);

    void update(BookComment bookComment);

    void remove(BookComment bookComment);

    Optional<BookComment> get(Long bookCommentId);

    List<BookComment> getAll();
}
