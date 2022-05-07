package com.example.book_catalog.service;

import com.example.book_catalog.dao.BookDao;
import com.example.book_catalog.domain.Book;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookDao bookDao;

    @Override
    public List<Book> getALl() {
        return bookDao.getAll();
    }

    @Override
    public Book getById(Long booksId) {
        return bookDao.get(booksId);
    }

    @Override
    public Long create(String bookName) {
        return bookDao.create(Book.builder().name(bookName).build());
    }

    @Override
    public void update(Long bookId, String bookName) {
        bookDao.update(Book.builder().id(bookId).name(bookName).build());
    }

    @Override
    public void remove(Long bookId) {
        bookDao.remove(Book.builder().id(bookId).build());
    }
}
