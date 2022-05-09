package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
import com.example.book_catalog.domain.Book;
import com.example.book_catalog.domain.BookComment;
import com.example.book_catalog.domain.Genre;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookCommentDaoImplTest {

    private static final Long EXIST_ID = 1L;
    private static final String EXIST_NAME = "bookCommentName";
    @Autowired
    BookCommentDao dao;

    @Test
    void create() {
        String bookCommentName = RandomString.make();
        BookComment bookComment = new BookComment();
        bookComment.setName(bookCommentName);
        Book book = new Book();
        Author author = new Author();
        author.setName("newAuthor");
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setName("newGenre");
        book.setGenre(genre);
        book.setName("newBook");
        bookComment.setBook(book);

        BookComment bookCommentId = dao.saveAndFlush(bookComment);
        assertThat(dao.findById(bookCommentId.getId())).isNotEmpty().get().extracting(BookComment::getName).isEqualTo(
                bookCommentName);
    }

    @Test
    void update() {
        BookComment bookCommentWithAnUglyTitle = dao.findById(EXIST_ID).get();
        String newbookCommentName = "this_name_is_better";
        bookCommentWithAnUglyTitle.setName(newbookCommentName);
        dao.flush();
        assertThat(dao.findById(EXIST_ID)).isNotEmpty().get().extracting(BookComment::getName).isEqualTo(newbookCommentName);
    }

    @Test
    void remove() {
        BookComment bookComment = dao.findById(EXIST_ID).get();

        dao.delete(bookComment);

        assertThat(dao.existsById(EXIST_ID)).isFalse();
    }

    @Test
    void get() {
        assertThat(dao.findById(EXIST_ID)).isNotEmpty().get().extracting(BookComment::getName).isEqualTo(EXIST_NAME);
    }

    @Test
    void getAll() {
        assertThat(dao.findAll()).hasSize(1);
    }
}