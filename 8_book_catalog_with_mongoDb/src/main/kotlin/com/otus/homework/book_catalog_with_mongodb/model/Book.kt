package com.otus.homework.book_catalog_with_mongodb.model

import org.springframework.data.mongodb.core.mapping.Document

@Document("books")
data class Book(
    val name: String,
    val author: Author,
    val genre: Genre,
    val bookComments: MutableList<BookComment> = mutableListOf()
) : UniqueIdentifier()