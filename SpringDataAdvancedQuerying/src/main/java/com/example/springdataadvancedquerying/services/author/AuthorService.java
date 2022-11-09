package com.example.springdataadvancedquerying.services.author;

import com.example.springdataadvancedquerying.domain.models.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AuthorService {

    boolean isDataSeeded();
    void seedAuthors(List<Author> authors);

    Author getRandomAuthor();
    List<Author> findAllByFirstNameEndingWith(String endsWith);
}
