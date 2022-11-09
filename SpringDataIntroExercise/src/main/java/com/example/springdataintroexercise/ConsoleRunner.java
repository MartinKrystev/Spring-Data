package com.example.springdataintroexercise;

import com.example.springdataintroexercise.domain.models.Author;
import com.example.springdataintroexercise.domain.models.Book;
import com.example.springdataintroexercise.repositories.AuthorRepository;
import com.example.springdataintroexercise.repositories.BookRepository;
import com.example.springdataintroexercise.services.author.AuthorService;
import com.example.springdataintroexercise.services.book.BookService;
import com.example.springdataintroexercise.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private LocalDate BOOK_YEAR_AFTER = LocalDate.of(2000, 1, 1);
    private LocalDate BOOK_YEAR_BEFORE = LocalDate.of(1991, 1, 1);

    private SeedService seedService;
    private BookService bookService;
    private AuthorService authorService;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //TODO ... uncomment the specific method to test the responsive problem
        this.seedService.seedAllData();

        /* Problem 01. Get all books after the year 2000. Print only their titles. */
//        this.getAllBooksAfterGivenYear();

        /* Problem 02. Get all authors with at least one book with release date before 1990. Print their first name and last name.*/
//        this.getAllAuthorsWithBooksReleasedBefore();

        /* Problem 03. Get all authors, ordered by the number of their books (descending). Print their first name, last name and book count.*/
//        this.getAuthorsByBookCount();

        /* Problem 04. Get all books from author George Powell, ordered by their release date (descending), then by book title (ascending). Print the book's title, release date and copies.*/
//        this.findByAuthorLastName("Powell");

    }

    private void getAllBooksAfterGivenYear() {
        this.bookService
                .findAllBooksByReleaseDateAfter(BOOK_YEAR_AFTER)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void getAllAuthorsWithBooksReleasedBefore() {
        this.authorService
                .findDistinctByBooksBefore(BOOK_YEAR_BEFORE)
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void getAuthorsByBookCount() {
        List<Author> authors = this.authorRepository.findAll();

        authors.stream()
                .sorted((a, b) -> b.getBooks().size() - a.getBooks().size())
                .forEach(a -> System.out.println(a.getFirstName() + " " +
                                                    a.getLastName() + " " +
                                                    a.getBooks().size()));
    }

    private void findByAuthorLastName(String lastName){
        List<Book> books = this.bookRepository.findByAuthorLastName(lastName);

        books.stream()
                .sorted((a, b) -> b.getReleaseDate().compareTo(a.getReleaseDate()))
                .sorted((a, b) -> a.getTitle().compareTo(b.getTitle()))
                .forEach(book -> System.out.printf("Book title: %s. Released on: %s. Number of copies: %d%n",
                        book.getTitle(), book.getReleaseDate(), book.getCopies()));

    }
}
