package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(AuthorDaoImpl.class)
class AuthorDaoImplTest {

    private static final Long EXIST_ID = 1L;
    private static final String EXIST_NAME = "the_first_author";
    @Autowired
    AuthorDaoImpl dao;

    @Test
    void create() {
        String authorName = RandomString.make();
        Author author = new Author();
        author.setName(authorName);

        long authorId = dao.create(author);
        assertThat(dao.get(authorId)).isNotEmpty().get().extracting(Author::getName).isEqualTo(authorName);
    }

    @Test
    void update() {
        Author authorWithAnUglyTitle = dao.get(EXIST_ID).get();
        String newAuthorName = "this_name_is_better";
        dao.update(authorWithAnUglyTitle.withName(newAuthorName));
        assertThat(dao.get(EXIST_ID)).isNotEmpty().get().extracting(Author::getName).isEqualTo(newAuthorName);
    }

    @Test
    void remove() {
        Author author = dao.get(EXIST_ID).get();

        dao.remove(author);

        assertThat(dao.get(EXIST_ID)).isEmpty();
    }

    @Test
    void get() {
        assertThat(dao.get(EXIST_ID)).isNotEmpty().get().extracting(Author::getName).isEqualTo(EXIST_NAME);
    }

    @Test
    void getAll() {
        assertThat(dao.getAll()).hasSize(1);
    }
}