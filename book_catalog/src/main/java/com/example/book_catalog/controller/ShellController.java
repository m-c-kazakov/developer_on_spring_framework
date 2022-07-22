package com.example.book_catalog.controller;

import com.example.book_catalog.domain.Book;
import com.example.book_catalog.domain.BookComment;
import com.example.book_catalog.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ShellController {

    BookService bookService;

    @ShellMethod(key = {"getAllBook", "all"}, value = "get all books")
    public List<Book> getALl() {
        return bookService.getALl();
    }

    @ShellMethod(key = {"getBookById", "gbbid"}, value = "get book by id")
    public Book getBookById(@ShellOption Long booksId) {
        return bookService.getById(booksId);
    }

    @ShellMethod(key = {"getCommentsByBookId", "gc"}, value = "get comments by book id")
    public List<BookComment> getCommentsByBookId(@ShellOption Long booksId) {
        return bookService.getCommentsByBookId(booksId);
    }

    @ShellMethod(key = {"createBook", "cb"}, value = "create book")
    public Long createBook(@ShellOption String bookName) {
        return bookService.create(bookName);
    }

    @ShellMethod(key = {"updateBook", "ub"}, value = "update book")
    public void updateBook(@ShellOption Long bookId, @ShellOption String bookName) {
        bookService.update(bookId, bookName);
    }

    @ShellMethod(key = {"removeBook", "rb"}, value = "remove book")
    public void remove(@ShellOption Long bookId) {
        bookService.remove(bookId);
    }
}
