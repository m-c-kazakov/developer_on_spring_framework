package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Book;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public long create(Book book) {
        em.persist(book);
        em.flush();
        return book.getId();
    }

    @Override
    public void update(Book book) {
        em.merge(book);
    }

    @Override
    public void remove(Book book) {
        em.remove(book);
    }

    @Override
    public Optional<Book> get(Long bookId) {
        return Optional.ofNullable(em.find(Book.class, bookId));
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}
