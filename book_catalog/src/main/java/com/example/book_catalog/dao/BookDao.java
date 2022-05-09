package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Long> {
}
