package com.example.springdataintroexercise.services.author;

import com.example.springdataintroexercise.domain.models.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AuthorService {

    boolean isDataSeeded();
    void seedAuthors(List<Author> authors);

    Author getRandomAuthor();

    List<Author> findDistinctByBooksBefore(LocalDate date);

//    List<Author> findAllOrderByBooks();

    Set<Author> getAllAuthors();

}
