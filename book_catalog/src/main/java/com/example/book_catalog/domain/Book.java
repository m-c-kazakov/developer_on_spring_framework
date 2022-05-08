package com.example.book_catalog.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Optional;


@ToString
@Getter
@Setter
@With
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    Author author;
    @ManyToOne(cascade = CascadeType.ALL)
    Genre genre;

    public Long getAuthorId() {
        return Optional.ofNullable(author).map(Author::getId).orElse(null);
    }

    public Long getGenreId() {
        return Optional.ofNullable(genre).map(Genre::getId).orElse(null);
    }
}
