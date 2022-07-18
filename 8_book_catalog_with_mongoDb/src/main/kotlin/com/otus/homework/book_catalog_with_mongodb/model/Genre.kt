package com.otus.homework.book_catalog_with_mongodb.model

data class Genre(
    val name: String,
    val books: MutableList<Book> = mutableListOf()
) : UniqueIdentifier()
