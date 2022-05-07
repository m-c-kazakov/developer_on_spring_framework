package com.example.book_catalog.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;


@ToString
@Getter
@With
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Book {

    Long id;
    String name;
}
