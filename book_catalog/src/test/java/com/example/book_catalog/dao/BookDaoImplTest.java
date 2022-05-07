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

    public static final long EXIST_BOOK_ID = 1L;
    public static final String EXIST_BOOK_NAME = "bookName";
    @Autowired
    BookDaoImpl bookDao;

    @Test
    void create() {

        String bookName = RandomString.make();
        Book book = Book.builder()
                .name(bookName)
                .author(Author.builder().id(1L).build())
                .genre(Genre.builder().id(1L).build())
                .build();
        long bookId = bookDao.create(book);
        assertThat(bookDao.get(bookId)).extracting(Book::getName).isEqualTo(bookName);
    }

    @Test
    void update() {

        Book bookWithAnUglyTitle = bookDao.get(EXIST_BOOK_ID);
        String newBookName = "this_name_is_better";
        bookDao.update(bookWithAnUglyTitle.withName(newBookName));
        assertThat(bookDao.get(EXIST_BOOK_ID)).extracting(Book::getName).isEqualTo(newBookName);
    }

    @Test
    void remove() {

        assertThatCode(() -> bookDao.get(EXIST_BOOK_ID))
                .doesNotThrowAnyException();

        bookDao.remove(Book.builder().id(EXIST_BOOK_ID).build());

        assertThatThrownBy(() -> bookDao.get(EXIST_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void get() {

        assertThat(bookDao.get(EXIST_BOOK_ID)).extracting(Book::getName).isEqualTo(EXIST_BOOK_NAME);

    }

    @Test
    void getAll() {
        assertThat(bookDao.getAll()).hasSize(1);
    }
}