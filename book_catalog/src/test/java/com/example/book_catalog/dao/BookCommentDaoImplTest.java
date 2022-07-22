package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
import com.example.book_catalog.domain.Book;
import com.example.book_catalog.domain.BookComment;
import com.example.book_catalog.domain.Genre;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BookCommentDaoImpl.class)
class BookCommentDaoImplTest {

    private static final Long EXIST_ID = 1L;
    private static final String EXIST_NAME = "bookCommentName";
    @Autowired
    BookCommentDaoImpl dao;

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

        long bookCommentId = dao.create(bookComment);
        assertThat(dao.get(bookCommentId)).isNotEmpty().get().extracting(BookComment::getName).isEqualTo(
                bookCommentName);
    }

    @Test
    void update() {
        BookComment bookCommentWithAnUglyTitle = dao.get(EXIST_ID).get();
        String newbookCommentName = "this_name_is_better";
        dao.update(bookCommentWithAnUglyTitle.withName(newbookCommentName));
        assertThat(dao.get(EXIST_ID)).isNotEmpty().get().extracting(BookComment::getName).isEqualTo(newbookCommentName);
    }

    @Test
    void remove() {
        BookComment bookComment = dao.get(EXIST_ID).get();

        dao.remove(bookComment);

        assertThat(dao.get(EXIST_ID)).isEmpty();
    }

    @Test
    void get() {
        assertThat(dao.get(EXIST_ID)).isNotEmpty().get().extracting(BookComment::getName).isEqualTo(EXIST_NAME);
    }

    @Test
    void getAll() {
        assertThat(dao.getAll()).hasSize(1);
    }
}