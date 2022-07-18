package com.example.book_catalog.dao;

import com.example.book_catalog.domain.BookComment;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookCommentDaoImpl implements BookCommentsDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public long create(BookComment bookComment) {
        em.persist(bookComment);
        em.flush();
        return bookComment.getId();
    }

    @Override
    public void update(BookComment bookComment) {
        em.merge(bookComment);
    }

    @Override
    public void remove(BookComment bookComment) {
        em.remove(bookComment);
    }

    @Override
    public Optional<BookComment> get(Long bookCommentId) {
        return Optional.ofNullable(em.find(BookComment.class, bookCommentId));
    }

    @Override
    public List<BookComment> getAll() {
        return em.createQuery("SELECT a FROM BookComment a", BookComment.class).getResultList();
    }
}
