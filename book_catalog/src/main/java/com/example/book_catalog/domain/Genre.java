package com.example.book_catalog.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ToString
@Getter
@With
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Genre {
    Long id;
    String name;
    List<Book> books;
}
