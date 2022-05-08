package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Author;
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
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    EntityManager em;
    @Override
    public long create(Author author) {
        em.persist(author);
        em.flush();
        return author.getId();
    }

    @Override
    public void update(Author author) {
        em.merge(author);
    }

    @Override
    public void remove(Author author) {
        em.remove(author);
    }

    @Override
    public Optional<Author> get(Long authorId) {
        return Optional.ofNullable(em.find(Author.class, authorId));
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }
}
