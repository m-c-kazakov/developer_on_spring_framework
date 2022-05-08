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
@Import(BookDaoImpl.class)
class BookDaoImplTest {

    public static final long EXIST_ID = 1L;
    public static final String EXIST_NAME = "bookName";
    @Autowired
    BookDaoImpl dao;

    @Test
    void create() {

        String bookName = RandomString.make();
        Book book = new Book();
        book.setName(bookName);
        Author author = new Author();
        author.setName("asdf");
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setName("asdf");
        book.setGenre(genre);



        long bookId = dao.create(book);
        assertThat(dao.get(bookId)).isNotEmpty().get().extracting(Book::getName).isEqualTo(bookName);
    }

    @Test
    void update() {

        Book bookWithAnUglyTitle = dao.get(EXIST_ID).get();
        String newBookName = "this_name_is_better";
        dao.update(bookWithAnUglyTitle.withName(newBookName));
        assertThat(dao.get(EXIST_ID)).isNotEmpty().get().extracting(Book::getName).isEqualTo(newBookName);
    }

    @Test
    void remove() {

        Book book = dao.get(EXIST_ID).get();
        dao.remove(book);

        assertThat(dao.get(EXIST_ID)).isEmpty();
    }

    @Test
    void get() {

        assertThat(dao.get(EXIST_ID)).isNotEmpty().get().extracting(Book::getName).isEqualTo(EXIST_NAME);

    }

    @Test
    void getAll() {
        assertThat(dao.getAll()).hasSize(1);
    }
}