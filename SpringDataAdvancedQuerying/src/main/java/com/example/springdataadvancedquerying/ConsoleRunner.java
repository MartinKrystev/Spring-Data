package com.example.springdataadvancedquerying;


import com.example.springdataadvancedquerying.domain.enums.AgeRestriction;
import com.example.springdataadvancedquerying.domain.enums.EditionType;
import com.example.springdataadvancedquerying.domain.models.Book;
import com.example.springdataadvancedquerying.services.author.AuthorService;
import com.example.springdataadvancedquerying.services.book.BookService;
import com.example.springdataadvancedquerying.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final Scanner scanner;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        /* filling the database */
        // this.seedService.seedAllData();

        /*P01. Books Titles by Age Restriction*/
//        this.p01_booksTitlesByAgeRestriction();

        /*P02. Golden Books*/
//        this.p02_goldenBooks();

        /*P03. Books by Price*/
//        this.p03_booksByPrice();

        /*P04. Not Released Books*/
//        this.p04_notReleasedBooks();

        /*P05. Books Released Before Date*/
//        this.p05_booksReleasedBeforeDate();

        /*P06. Authors Search*/
//        this.p06_authorsSearch();

        /*P07. Books Search*/
//        this.p07_bookSearch();

        /*P08. Book Titles Search*/
//        this.p08_bookTitlesSearch();

        /*P09. Count Books*/
//        this.p09_countBooks();

        /*P10. Total Book Copies*/
//        this.p10_totalBookCopies();

        /*P11. Reduced Book*/
//        this.p11_reducedBook();
    }

    private void p01_booksTitlesByAgeRestriction() {
        String ageRestrictionInput = scanner.nextLine();
        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionInput.toUpperCase());
        List<Book> resultList = this.bookService.findAllByAgeRestriction(ageRestriction);

        resultList.forEach(r -> System.out.println(r.getTitle()));
    }

    private void p02_goldenBooks() {
        EditionType editionType = EditionType.GOLD;
        int copies = 5_000;

        this.bookService.findAllByEditionTypeAndCopiesLessThan(editionType, copies)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void p03_booksByPrice() {
        BigDecimal min = BigDecimal.valueOf(5L);
        BigDecimal max = BigDecimal.valueOf(40L);

        this.bookService.findAllByPriceLessThanOrPriceGreaterThan(min, max)
                .stream().map(Book::getBookTitleAndPriceFormatted)
                .forEach(System.out::println);
    }

    private void p04_notReleasedBooks() {
        int year = 2000;

        this.bookService.findAllByReleaseDateIsNot(LocalDate.of(year, 1, 1))
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void p05_booksReleasedBeforeDate() {
        String[] dateInput = scanner.nextLine().split("-");
        int year = Integer.parseInt(dateInput[2]);
        int month = Integer.parseInt(dateInput[1]);
        int day = Integer.parseInt(dateInput[0]);

        LocalDate date = LocalDate.of(year, month, day);

        this.bookService.findAllByReleaseDateBefore(date)
                .forEach(b -> System.out.println(b.getBookTitleEditionTypeAndPriceFormatted()));
    }

    public void p06_authorsSearch() {
        String endsWithInput = scanner.nextLine();

        this.authorService.findAllByFirstNameEndingWith(endsWithInput)
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    public void p07_bookSearch() {
        String searched = scanner.nextLine();

        this.bookService.findAllByTitleContainingIgnoreCase(searched)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    public void p08_bookTitlesSearch() {
        String searched = scanner.nextLine();

        this.bookService.findAllByAuthorLastNameStartingWith(searched)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    public void p09_countBooks() {
        int titleLength = Integer.parseInt(scanner.nextLine());

        Integer bookCount = this.bookService.findCountOfBooksWithTitleLongerThan(titleLength);

        System.out.printf("There are %d books with longer title than %d symbols",
                bookCount, titleLength);
        System.out.println();

    }

    public void p10_totalBookCopies(){
        this.bookService.findAll()
                .stream()
                .sorted((a, b) -> b.getCopies().compareTo(a.getCopies()))
                .forEach(b -> System.out.println(b.getAuthorsFirstLastNameAndCopiesFormatted()));
    }

    public void p11_reducedBook() {
        String searchedTitle = scanner.nextLine();

        System.out.println(this.bookService.findFirstByTitle(searchedTitle).getReducedBookInformation());
    }
}
