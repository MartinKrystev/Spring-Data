package com.example.springdataintroexercise.services.book;

import com.example.springdataintroexercise.domain.models.Book;
import com.example.springdataintroexercise.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooksByReleaseDateAfter(LocalDate date) {
        return this.bookRepository.findAllBooksByReleaseDateAfter(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAll(books);
    }

    @Override
    public List<Book> findByAuthorLastName(String lastName) {
        return this.bookRepository.getBooksByAuthor_LastName(lastName);
    }

}
