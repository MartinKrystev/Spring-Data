package com.example.springdataadvancedquerying.repositories;

import com.example.springdataadvancedquerying.domain.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorById(Long id);
    Optional<List<Author>> findAllByFirstNameEndingWith(String endsWith);

}

