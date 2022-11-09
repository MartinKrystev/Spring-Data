package com.example.springdataintroexercise.repositories;

import com.example.springdataintroexercise.domain.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findAllBooksByReleaseDateAfter(LocalDate localDate);

    List<Book> findByAuthorLastName(String lastName);

    List<Book> getBooksByAuthor_LastName(String lastName);
}
