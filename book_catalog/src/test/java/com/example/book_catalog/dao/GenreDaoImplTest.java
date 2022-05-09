package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Genre;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class GenreDaoImplTest {

    private static final Long EXIST_ID = 1L;
    private static final String EXIST_NAME = "the_first_genre";
    @Autowired
    GenreDao dao;

    @Test
    void create() {
        String genreName = RandomString.make();

        Genre genre = new Genre();
        genre.setName(genreName);

        Genre genreWithId = dao.saveAndFlush(genre);
        assertThat(dao.findById(genreWithId.getId())).isNotEmpty().get().extracting(Genre::getName).isEqualTo(genreName);
    }

    @Test
    void update() {
        Genre genreWithAnUglyTitle = dao.findById(EXIST_ID).get();
        String newGenreName = "this_name_is_better";
        genreWithAnUglyTitle.setName(newGenreName);
        dao.flush();
        assertThat(dao.findById(EXIST_ID)).isNotEmpty().get().extracting(Genre::getName).isEqualTo(newGenreName);
    }

    @Test
    void remove() {

        Genre genre = dao.findById(EXIST_ID).get();
        dao.delete(genre);

        assertThat(dao.existsById(EXIST_ID)).isFalse();

    }

    @Test
    void get() {
        assertThat(dao.findById(EXIST_ID)).isNotEmpty().get().extracting(Genre::getName).isEqualTo(EXIST_NAME);
    }

    @Test
    void getAll() {
        assertThat(dao.findAll()).hasSize(1);
    }
}