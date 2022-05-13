package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(AuthorDaoImpl.class)
class AuthorDaoImplTest {

    private static final Long EXIST_ID = 1L;
    private static final String EXIST_NAME = "the_first_author";
    @Autowired
    AuthorDaoImpl dao;

    @Test
    void create() {
        String authorName = RandomString.make();
        Author author = Author.builder()
                .name(authorName)
                .build();
        long authorId = dao.create(author);
        assertThat(dao.get(authorId)).extracting(Author::getName).isEqualTo(authorName);
    }

    @Test
    void update() {
        Author authorWithAnUglyTitle = dao.get(EXIST_ID);
        String newAuthorName = "this_name_is_better";
        dao.update(authorWithAnUglyTitle.withName(newAuthorName));
        assertThat(dao.get(EXIST_ID)).extracting(Author::getName).isEqualTo(newAuthorName);
    }

    @Test
    void remove() {
        assertThatCode(() -> dao.get(EXIST_ID))
                .doesNotThrowAnyException();

        dao.remove(Author.builder().id(EXIST_ID).build());

        assertThatThrownBy(() -> dao.get(EXIST_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void get() {
        assertThat(dao.get(EXIST_ID)).extracting(Author::getName).isEqualTo(EXIST_NAME);
    }

    @Test
    void getAll() {
        assertThat(dao.getAll()).hasSize(1);
    }
}