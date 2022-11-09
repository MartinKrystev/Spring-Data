package com.example.springdataadvancedquerying.services.seed;

import com.example.springdataadvancedquerying.domain.enums.AgeRestriction;
import com.example.springdataadvancedquerying.domain.enums.EditionType;
import com.example.springdataadvancedquerying.domain.models.Author;
import com.example.springdataadvancedquerying.domain.models.Book;
import com.example.springdataadvancedquerying.domain.models.Category;
import com.example.springdataadvancedquerying.services.author.AuthorService;
import com.example.springdataadvancedquerying.services.book.BookService;
import com.example.springdataadvancedquerying.services.category.CategoryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.springdataadvancedquerying.constants.FilePath.*;

@Component
public class SeedServiceImpl implements SeedService {

    private AuthorService authorService;
    private BookService bookService;
    private CategoryService categoryService;

    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (!this.authorService.isDataSeeded()) {
            this.authorService
                    .seedAuthors(Files.readAllLines(Path.of(RESOURCE_PATH + AUTHORS_FILE_NAME))
                            .stream()
                            .filter(s -> !s.isBlank())
                            .map(s -> s.split(" "))
                            .map(fullName -> new Author(fullName[0], fullName[1]))
                            .collect(Collectors.toList()));
        }
    }
    @Override
    public void seedCategories() throws IOException {
        if (!categoryService.isDataSeeded()) {
            this.categoryService.seedCategories(Files.readAllLines(Path.of(RESOURCE_PATH + CATEGORIES_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(name -> new Category(name))
                    .collect(Collectors.toList()));
        }
    }
    @Override
    public void seedBooks() throws IOException {
        List<Book> books = Files.readAllLines(Path.of(RESOURCE_PATH + BOOKS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> {
                    String[] data = s.split("\\s+");
                    Author author = this.authorService.getRandomAuthor();
                    EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
                    LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                    int copies = Integer.parseInt(data[2]);
                    BigDecimal price = new BigDecimal(data[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
                    String title = Arrays.stream(data)
                            .skip(5)
                            .collect(Collectors.joining(" "));
                    Set<Category> categories = categoryService.getRandomCategories();

                    return new Book(title, "", editionType, price, copies, releaseDate, ageRestriction, author, categories);
                })
                .collect(Collectors.toList());

        this.bookService.seedBooks(books);
    }

}
