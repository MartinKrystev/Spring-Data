package com.example.springdataadvancedquerying.services.book;

import com.example.springdataadvancedquerying.domain.enums.AgeRestriction;
import com.example.springdataadvancedquerying.domain.enums.EditionType;
import com.example.springdataadvancedquerying.domain.models.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookService {
    void seedBooks(List<Book> books);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);
    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal min, BigDecimal max);
    List<Book> findAllByReleaseDateIsNot(LocalDate date);
    List<Book> findAllByReleaseDateBefore(LocalDate date);
    List<Book> findAllByTitleContainingIgnoreCase(String searched);
    List<Book> findAllByAuthorLastNameStartingWith(String searched);
    Integer findCountOfBooksWithTitleLongerThan(Integer length);
    List<Book> findAll();
    Book findFirstByTitle(String title);
}
