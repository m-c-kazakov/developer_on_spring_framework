package com.example.book_catalog.dao;

import com.example.book_catalog.dao.mappers.AuthorMapper;
import com.example.book_catalog.domain.Author;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorDaoImpl implements AuthorDao {

    NamedParameterJdbcOperations jdbcOperations;
    @Override
    public long create(Author author) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, ? extends Serializable> values = Map.of(
                "NAME", author.getName()
        );
        
        jdbcOperations
                .update("INSERT INTO AUTHORS ( NAME ) VALUES(:NAME )",
                        new MapSqlParameterSource(values),
                        keyHolder);
        return requireNonNull(keyHolder.getKey()).longValue();

    }

    @Override
    public void update(Author author) {
        jdbcOperations.update("UPDATE AUTHORS SET name = :name WHERE id = :id", Map.of("id", author.getId(), "name", author.getName()));
    }

    @Override
    public void remove(Author author) {
        jdbcOperations.update("DELETE FROM AUTHORS WHERE id = :id", Map.of("id", author.getId()));
    }

    @Override
    public Author get(Long authorId) {

        return jdbcOperations.queryForObject("SELECT id, name FROM AUTHORS WHERE id = :id", Map.of("id", authorId), new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {

        return jdbcOperations.query("SELECT id, name FROM AUTHORS", new AuthorMapper());
    }
}
