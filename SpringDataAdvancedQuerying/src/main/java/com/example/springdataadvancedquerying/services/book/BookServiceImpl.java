package com.example.springdataadvancedquerying.services.book;

import com.example.springdataadvancedquerying.domain.enums.AgeRestriction;
import com.example.springdataadvancedquerying.domain.enums.EditionType;
import com.example.springdataadvancedquerying.domain.models.Book;
import com.example.springdataadvancedquerying.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAll(books);
    }
    @Override
    public List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(ageRestriction)
                .orElseThrow(NoSuchElementException::new);
    }
    @Override
    public List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies) {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, copies)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal min, BigDecimal max) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(min, max)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByReleaseDateIsNot(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateIsNot(date)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateBefore(date)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByTitleContainingIgnoreCase(String searched) {
        return this.bookRepository.findAllByTitleContainingIgnoreCase(searched)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByAuthorLastNameStartingWith(String searched) {
        return this.bookRepository.findAllByAuthorLastNameStartingWith(searched)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Integer findCountOfBooksWithTitleLongerThan(Integer length) {
        return this.bookRepository.findCountOfBooksWithTitleLongerThan(length)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findFirstByTitle(String title) {
        return this.bookRepository.findFirstByTitle(title)
                .orElseThrow(NoSuchElementException::new);
    }

}
