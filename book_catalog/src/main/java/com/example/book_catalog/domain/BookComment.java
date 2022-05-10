package com.example.book_catalog.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@ToString(exclude = "book")
@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book_comments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    Book book;
}
