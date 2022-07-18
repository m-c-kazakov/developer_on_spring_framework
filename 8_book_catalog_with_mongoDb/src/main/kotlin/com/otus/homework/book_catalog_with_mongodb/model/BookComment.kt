package com.otus.homework.book_catalog_with_mongodb.model

import org.springframework.data.mongodb.core.mapping.Document

@Document("comments")
data class BookComment(
    val name: String,
    val bookId: String
) : UniqueIdentifier()