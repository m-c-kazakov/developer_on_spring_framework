package com.example.book_catalog.service;

import com.example.book_catalog.dao.BookDao;
import com.example.book_catalog.domain.Book;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public List<Book> getALl() {
        return bookDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(Long booksId) {
        return bookDao.get(booksId).orElseThrow(() -> new RuntimeException(
                String.format("Книги с Id=%d не существует.", booksId)));
    }

    @Override
    @Transactional
    public Long create(String bookName) {
        Book book = new Book();
        book.setName(bookName);
        return bookDao.create(book);
    }

    @Override
    @Transactional
    public void update(Long bookId, String bookName) {

        bookDao.get(bookId)
                .ifPresentOrElse(
                        book -> book.setName(bookName),
                        () -> {
                            throw new RuntimeException(
                                    String.format("Книги с Id=%d не существует.", bookId));
                        }
                );
    }

    @Override
    @Transactional
    public void remove(Long bookId) {
        Book book = new Book();
        book.setId(bookId);
        bookDao.remove(book);
    }
}
