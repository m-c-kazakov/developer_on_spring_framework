package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
import com.example.book_catalog.domain.Book;
import com.example.book_catalog.domain.Genre;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookDaoImplTest {

    public static final long EXIST_ID = 1L;
    public static final String EXIST_NAME = "bookName";
    @Autowired
    BookDao dao;

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



        Book bookWithId = dao.saveAndFlush(book);
        assertThat(dao.findById(bookWithId.getId())).isNotEmpty().get().extracting(Book::getName).isEqualTo(bookName);
    }

    @Test
    void update() {

        Book bookWithAnUglyTitle = dao.findById(EXIST_ID).get();
        String newBookName = "this_name_is_better";
        bookWithAnUglyTitle.setName(newBookName);
        dao.flush();
        assertThat(dao.findById(EXIST_ID)).isNotEmpty().get().extracting(Book::getName).isEqualTo(newBookName);
    }

    @Test
    void remove() {

        Book book = dao.findById(EXIST_ID).get();
        dao.delete(book);

        assertThat(dao.existsById(EXIST_ID)).isFalse();
    }

    @Test
    void get() {

        assertThat(dao.findById(EXIST_ID)).isNotEmpty().get().extracting(Book::getName).isEqualTo(EXIST_NAME);

    }

    @Test
    void getAll() {
        assertThat(dao.findAll()).hasSize(1);
    }
}