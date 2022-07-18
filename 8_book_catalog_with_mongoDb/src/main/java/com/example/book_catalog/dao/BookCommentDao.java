package com.example.book_catalog.dao;

import com.example.book_catalog.domain.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCommentDao extends JpaRepository<BookComment, Long> {
}
