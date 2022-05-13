package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
import com.example.book_catalog.domain.Book;
import com.example.book_catalog.domain.Genre;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(BookDaoImpl.class)
class BookDaoImplTest {

    public static final long EXIST_ID = 1L;
    public static final String EXIST_NAME = "bookName";
    @Autowired
    BookDaoImpl dao;

    @Test
    void create() {

        String bookName = RandomString.make();
        Book book = Book.builder()
                .name(bookName)
                .author(Author.builder().id(1L).build())
                .genre(Genre.builder().id(1L).build())
                .build();
        long bookId = dao.create(book);
        assertThat(dao.get(bookId)).extracting(Book::getName).isEqualTo(bookName);
    }

    @Test
    void update() {

        Book bookWithAnUglyTitle = dao.get(EXIST_ID);
        String newBookName = "this_name_is_better";
        dao.update(bookWithAnUglyTitle.withName(newBookName));
        assertThat(dao.get(EXIST_ID)).extracting(Book::getName).isEqualTo(newBookName);
    }

    @Test
    void remove() {

        assertThatCode(() -> dao.get(EXIST_ID))
                .doesNotThrowAnyException();

        dao.remove(Book.builder().id(EXIST_ID).build());

        assertThatThrownBy(() -> dao.get(EXIST_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void get() {

        assertThat(dao.get(EXIST_ID)).extracting(Book::getName).isEqualTo(EXIST_NAME);

    }

    @Test
    void getAll() {
        assertThat(dao.getAll()).hasSize(1);
    }
}