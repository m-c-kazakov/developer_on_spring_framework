package com.example.book_catalog.service;

import com.example.book_catalog.dao.BookDao;
import com.example.book_catalog.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {

    @Autowired
    BookServiceImpl bookService;
    @MockBean
    BookDao bookDao;

    @Test
    void getALl() {
        Book book = new Book();
        book.setId(1L);
        book.setName("BookName");
        Mockito.doReturn(List.of(book)).when(bookDao).getAll();

        assertThat(bookService.getALl()).hasSize(1).first().isEqualTo(book);
        verify(bookDao, times(1)).getAll();
    }

    @Test
    void getById$Success() {
        Book book = new Book();
        book.setId(1L);
        book.setName("BookName");

        doReturn(Optional.ofNullable(book)).when(bookDao).get(anyLong());

        assertThat(bookService.getById(anyLong())).usingRecursiveComparison().isEqualTo(book);
        verify(bookDao, times(1)).get(anyLong());
    }
    @Test
    void getById$Throw() {

        doReturn(Optional.empty()).when(bookDao).get(anyLong());

        assertThatThrownBy(() -> bookService.getById(anyLong())).isInstanceOf(RuntimeException.class);
        verify(bookDao, times(1)).get(anyLong());
    }

    @Test
    void create() {
        doReturn(1L).when(bookDao).create(any(Book.class));
        assertThat(bookService.create(anyString())).isEqualTo(1L);
        verify(bookDao, times(1)).create(any(Book.class));
    }

    @Test
    void update() {
        Book book = new Book();
        book.setId(1L);
        book.setName("BookName");

        doReturn(Optional.ofNullable(book)).when(bookDao).get(anyLong());

        assertThatCode(() -> bookService.update(anyLong(), "afd"))
                .doesNotThrowAnyException();
    }

    @Test
    void remove() {
        doNothing().when(bookDao).remove(any(Book.class));
        assertThatCode(() -> bookService.remove(anyLong()))
                .doesNotThrowAnyException();
    }
}