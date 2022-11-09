package com.example.springdataadvancedquerying.repositories;

import com.example.springdataadvancedquerying.domain.enums.AgeRestriction;
import com.example.springdataadvancedquerying.domain.enums.EditionType;
import com.example.springdataadvancedquerying.domain.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<List<Book>> findAllByAgeRestriction(AgeRestriction ageRestriction);
    Optional<List<Book>> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);
    Optional<List<Book>> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal min, BigDecimal max);
    Optional<List<Book>> findAllByReleaseDateIsNot(LocalDate date);
    Optional<List<Book>> findAllByReleaseDateBefore(LocalDate date);
    Optional<List<Book>> findAllByTitleContainingIgnoreCase(String searched);
    Optional<List<Book>> findAllByAuthorLastNameStartingWith(String searched);
    @Query(value = "SELECT COUNT(b) FROM Book AS b WHERE LENGTH(b.title) > :number")
    Optional<Integer> findCountOfBooksWithTitleLongerThan(@Param(value = "number") Integer length);

    @Override
    List<Book> findAll();
    Optional<Book> findFirstByTitle(String title);
}
