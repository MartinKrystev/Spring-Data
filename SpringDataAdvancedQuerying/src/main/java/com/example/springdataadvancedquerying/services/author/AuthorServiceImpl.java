package com.example.springdataadvancedquerying.services.author;

import com.example.springdataadvancedquerying.domain.models.Author;
import com.example.springdataadvancedquerying.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService{
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean isDataSeeded() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public void seedAuthors(List<Author> authors) {
        this.authorRepository.saveAll(authors);
    }

    @Override
    public Author getRandomAuthor() {
        long count = this.authorRepository.count();
        if (count != 0) {
            long randomId = new Random().nextLong(1L, count) + 1L;
            return this.authorRepository.findAuthorById(randomId).orElseThrow(NoSuchElementException::new);
        }

        throw new RuntimeException();
    }

    @Override
    public List<Author> findAllByFirstNameEndingWith(String endsWith) {
        return this.authorRepository.findAllByFirstNameEndingWith(endsWith)
                .orElseThrow(NoSuchElementException::new);
    }


}
