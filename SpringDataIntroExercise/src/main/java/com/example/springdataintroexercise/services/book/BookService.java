package com.example.springdataintroexercise.services.book;

import com.example.springdataintroexercise.domain.models.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllBooksByReleaseDateAfter(LocalDate localDate);
    void seedBooks(List<Book> books);

    List<Book> findByAuthorLastName(String name);
}
