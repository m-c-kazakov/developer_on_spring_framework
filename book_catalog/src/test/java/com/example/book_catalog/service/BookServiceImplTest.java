package com.example.book_catalog.service;

import com.example.book_catalog.domain.Author;
import com.example.book_catalog.domain.Book;
import com.example.book_catalog.domain.BookComment;
import com.example.book_catalog.domain.Genre;
import com.example.book_catalog.intagration.IntegrationTestBased;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BookServiceImplTest extends IntegrationTestBased {

    @Autowired
    BookServiceImpl bookService;

    @Test
    void getALl() {
        String bookName = RandomString.make();
        Book book = new Book();
        book.setName(bookName);
        Author author = new Author();
        author.setName("asdf");
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setName("asdf");
        book.setGenre(genre);
        book.setBookComments(List.of(BookComment.builder().name("adsf").book(book).build()));
        Book entity = bookService.create(book);
        book.setId(entity.getId());
        assertThat(bookService.getALl()).isNotEmpty();
    }


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
        book.setBookComments(List.of(BookComment.builder().name("adsf").book(book).build()));
        Book entity = bookService.create(book);
        book.setId(entity.getId());
        assertThat(bookService.getById(entity.getId())).isNotNull();
    }

    @Test
    void update() {
        String bookName = RandomString.make();
        Book book = new Book();
        book.setName(bookName);
        Author author = new Author();
        author.setName("asdf");
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setName("asdf");
        book.setGenre(genre);
        book.setBookComments(List.of(BookComment.builder().name("adsf").book(book).build()));
        Book entity = bookService.create(book);
        book.setId(entity.getId());


        String randomName = RandomString.make();
        bookService.update(entity.getId(), randomName);


        assertThat(bookService.getById(entity.getId())).isNotNull()
                .extracting(Book::getName)
                .isEqualTo(randomName);
    }

    @Test
    void remove() {

        String bookName = RandomString.make();
        Book book = new Book();
        book.setName(bookName);
        Author author = new Author();
        author.setName("asdf");
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setName("asdf");
        book.setGenre(genre);
        book.setBookComments(List.of(BookComment.builder().name("adsf").book(book).build()));
        Book entity = bookService.create(book);

        assertThatCode(() -> bookService.remove(entity.getId()))
                .doesNotThrowAnyException();
    }

    @Test
    void getCommentsByBookId() {
        String bookName = RandomString.make();
        Book book = new Book();
        book.setName(bookName);
        Author author = new Author();
        author.setName("asdf");
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setName("asdf");
        book.setGenre(genre);
        String comment1 = RandomString.make();
        String comment2 = RandomString.make();
        book.setBookComments(List.of(
                BookComment.builder().name(comment1).book(book).build(),
                BookComment.builder().name(comment2).book(book).build()
        ));
        Book entity = bookService.create(book);

        assertThat(bookService.getCommentsByBookId(entity.getId()))
                .isNotEmpty()
                .extracting(BookComment::getName)
                .contains(comment1, comment2);
    }
}