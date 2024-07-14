package com.aluraChallenge.literalura.repository;

import com.aluraChallenge.literalura.models.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Long> {
    Optional<Authors> findByNombreContainsIgnoreCase(String nombre);

}