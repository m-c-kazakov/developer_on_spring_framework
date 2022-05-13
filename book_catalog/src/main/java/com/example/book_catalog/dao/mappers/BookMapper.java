package com.example.book_catalog.dao.mappers;

import com.example.book_catalog.domain.Author;
import com.example.book_catalog.domain.Book;
import com.example.book_catalog.domain.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        long authorId = rs.getLong("AUTHOR_ID");
        String authorName = rs.getString("AUTHOR_NAME");
        long genreId = rs.getLong("GENRE_ID");
        String genreName = rs.getString("GENRE_NAME");
        return Book.builder()
                .id(id)
                .name(name)
                .author(Author.builder()
                        .id(authorId)
                        .name(authorName)
                        .build())
                .genre(Genre.builder()
                        .id(genreId)
                        .name(genreName)
                        .build())
                .build();
    }
}
