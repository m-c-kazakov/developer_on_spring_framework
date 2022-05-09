package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Long> {

}
