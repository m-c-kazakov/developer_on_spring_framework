package com.example.book_catalog.dao;

import com.example.book_catalog.domain.Genre;
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
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public long create(Genre genre) {
        em.persist(genre);
        em.flush();
        return genre.getId();

    }

    @Override
    public void update(Genre genre) {
        em.merge(genre);
    }

    @Override
    public void remove(Genre genre) {
        em.remove(genre);
    }

    @Override
    public Optional<Genre> get(Long genreId) {
        return Optional.ofNullable(em.find(Genre.class, genreId));
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }
}
