package com.example.book_catalog.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Optional;


@ToString
@Getter
@With
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Book {

    Long id;
    String name;
    Author author;
    Genre genre;

    public Long getAuthorId() {
        return Optional.ofNullable(author).map(Author::getId).orElse(null);
    }

    public Long getGenreId() {
        return Optional.ofNullable(genre).map(Genre::getId).orElse(null);
    }
}
