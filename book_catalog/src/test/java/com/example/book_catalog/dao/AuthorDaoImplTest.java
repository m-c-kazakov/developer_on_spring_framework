package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorDaoImplTest {

    private static final Long EXIST_ID = 1L;
    private static final String EXIST_NAME = "the_first_author";
    @Autowired
    AuthorDao dao;

    @Test
    void create() {
        String authorName = RandomString.make();
        Author author = new Author();
        author.setName(authorName);

        Author authorWithId = dao.saveAndFlush(author);
        assertThat(dao.findById(authorWithId.getId())).isNotEmpty().get().extracting(Author::getName).isEqualTo(authorName);
    }

    @Test
    void update() {
        Author authorWithAnUglyTitle = dao.findById(EXIST_ID).get();
        String newAuthorName = "this_name_is_better";
        authorWithAnUglyTitle.setName(newAuthorName);
        dao.flush();
        assertThat(dao.findById(EXIST_ID)).isNotEmpty().get().extracting(Author::getName).isEqualTo(newAuthorName);
    }

    @Test
    void remove() {
        Author author = dao.findById(EXIST_ID).get();

        dao.delete(author);

        assertThat(dao.existsById(EXIST_ID)).isFalse();
    }

    @Test
    void get() {
        assertThat(dao.findById(EXIST_ID)).isNotEmpty().get().extracting(Author::getName).isEqualTo(EXIST_NAME);
    }

    @Test
    void getAll() {
        assertThat(dao.findAll()).hasSize(1);
    }
}