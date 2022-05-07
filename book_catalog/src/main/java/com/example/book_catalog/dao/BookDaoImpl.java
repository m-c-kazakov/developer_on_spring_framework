package com.example.book_catalog.dao;

import com.example.book_catalog.dao.mappers.BookMapper;
import com.example.book_catalog.domain.Book;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookDaoImpl implements BookDao {

    NamedParameterJdbcOperations jdbcOperations;

    @Override
    public long create(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("INSERT INTO BOOKS (name) VALUES(:name)", new MapSqlParameterSource(Map.of("name", book.getName())), keyHolder);
        return requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public void update(Book book) {
        jdbcOperations.update("UPDATE BOOKS SET name = :name WHERE id = :id", Map.of("id", book.getId(), "name", book.getName()));

    }

    @Override
    public void remove(Book book) {
        jdbcOperations.update("DELETE FROM BOOKS WHERE id = :id", Map.of("id", book.getId()));
    }

    @Override
    public Book get(Long bookId) {
        return jdbcOperations.queryForObject("SELECT * FROM BOOKS WHERE id = :id", Map.of("id", bookId), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query("SELECT id, name FROM BOOKS", new BookMapper());

    }
}
