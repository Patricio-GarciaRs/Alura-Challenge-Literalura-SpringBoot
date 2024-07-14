package com.aluraChallenge.literalura.repository;

import com.aluraChallenge.literalura.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
    Optional<Books> findByTituloContainsIgnoreCase(String titulo);
}