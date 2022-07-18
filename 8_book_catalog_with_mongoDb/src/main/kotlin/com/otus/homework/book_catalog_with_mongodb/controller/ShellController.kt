package com.otus.homework.book_catalog_with_mongodb.controller

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
class ShellController(
//    private val bookService: BookService
) {

    @ShellMethod(value = "List all books.", key = ["books", "book list"])
    fun getAllBooks(): String = "bookService.getAll().view()"

//    @get:ShellMethod(key = ["getAllBook", "all"], value = "get all books")
//    val aLl: List<Any>
//        get() = bookService.getALl()
//
//    @ShellMethod(key = ["getBookById", "gbbid"], value = "get book by id")
//    fun getBookById(@ShellOption booksId: Long?): Book {
//        return bookService.getById(booksId)
//    }
//
//    @ShellMethod(key = ["createBook", "cb"], value = "create book")
//    fun createBook(@ShellOption bookName: String?): Long {
//        return bookService.create(bookName)
//    }
//
//    @ShellMethod(key = ["updateBook", "ub"], value = "update book")
//    fun updateBook(@ShellOption bookId: Long?, @ShellOption bookName: String?) {
//        bookService.update(bookId, bookName)
//    }
//
//    @ShellMethod(key = ["removeBook", "rb"], value = "remove book")
//    fun remove(@ShellOption bookId: Long?) {
//        bookService.remove(bookId)
//    }
}