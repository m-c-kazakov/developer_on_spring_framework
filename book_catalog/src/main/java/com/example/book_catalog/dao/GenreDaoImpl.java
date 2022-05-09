package com.example.book_catalog.dao;

import com.example.book_catalog.dao.mappers.GenreMapper;
import com.example.book_catalog.domain.Genre;
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
public class GenreDaoImpl implements GenreDao {

    NamedParameterJdbcOperations jdbcOperations;

    @Override
    public long create(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, ? extends Serializable> values = Map.of("NAME", genre.getName());

        jdbcOperations.update("INSERT INTO GENRES ( NAME ) VALUES(:NAME)", new MapSqlParameterSource(values), keyHolder);
        return requireNonNull(keyHolder.getKey()).longValue();

    }

    @Override
    public void update(Genre genre) {
        jdbcOperations.update("UPDATE GENRES SET name = :name WHERE id = :id",
                Map.of("id", genre.getId(), "name", genre.getName()));
    }

    @Override
    public void remove(Genre genre) {
        jdbcOperations.update("DELETE FROM GENRES WHERE id = :id", Map.of("id", genre.getId()));
    }

    @Override
    public Genre get(Long genreId) {

        return jdbcOperations.queryForObject("SELECT id, name FROM GENRES WHERE id = :id", Map.of("id", genreId), new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbcOperations.query("SELECT id, name FROM GENRES", new GenreMapper());
    }
}
