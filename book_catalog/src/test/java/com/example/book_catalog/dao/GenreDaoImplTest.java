package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
import com.example.book_catalog.domain.Book;
import com.example.book_catalog.domain.Genre;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(GenreDaoImpl.class)
class GenreDaoImplTest {

    private static final Long EXIST_ID = 1L;
    private static final String EXIST_NAME = "the_first_genre";
    @Autowired
    GenreDaoImpl dao;

    @Test
    void create() {
        String genreName = RandomString.make();

        Genre genre = new Genre();
        genre.setName(genreName);

        long genreId = dao.create(genre);
        assertThat(dao.get(genreId)).isNotEmpty().get().extracting(Genre::getName).isEqualTo(genreName);
    }

    @Test
    void update() {
        Genre genreWithAnUglyTitle = dao.get(EXIST_ID).get();
        String newGenreName = "this_name_is_better";
        dao.update(genreWithAnUglyTitle.withName(newGenreName));
        assertThat(dao.get(EXIST_ID)).isNotEmpty().get().extracting(Genre::getName).isEqualTo(newGenreName);
    }

    @Test
    void remove() {

        Genre genre = dao.get(EXIST_ID).get();
        dao.remove(genre);

        assertThat(dao.get(EXIST_ID)).isEmpty();

    }

    @Test
    void get() {
        assertThat(dao.get(EXIST_ID)).isNotEmpty().get().extracting(Genre::getName).isEqualTo(EXIST_NAME);
    }

    @Test
    void getAll() {
        assertThat(dao.getAll()).hasSize(1);
    }
}