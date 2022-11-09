package com.example.springdataadvancedquerying.domain.models;

import com.example.springdataadvancedquerying.domain.enums.AgeRestriction;
import com.example.springdataadvancedquerying.domain.enums.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private AgeRestriction ageRestriction;

    @ManyToOne
    private Author author;

    @ManyToMany
    private Set<Category> categories;

    public Book() {
        this.categories = new HashSet<>();
    }

    public Book(String title, String description, EditionType editionType, BigDecimal price,
                Integer copies, LocalDate releaseDate, AgeRestriction ageRestriction,
                Author author, Set<Category> categories) {
        this();

        this.title = title;
        this.description = description;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getBookTitleAndPriceFormatted() {
        return this.title + " - $" + this.price;
    }

    public String getBookTitleEditionTypeAndPriceFormatted() {
        return this.title + " " + this.editionType + " " + this.price;
    }

    public String getAuthorsFirstLastNameAndCopiesFormatted() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getAuthor().getLastName()).append(" ");
        sb.append(this.getAuthor().getFirstName()).append(" - ");
        sb.append(this.getCopies());

        return sb.toString();
    }

    public String getReducedBookInformation() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getTitle()).append(" ");
        sb.append(this.getEditionType()).append(" ");
        sb.append(this.getAgeRestriction()).append(" ");
        sb.append(this.getPrice());

        return sb.toString();
    }
}
