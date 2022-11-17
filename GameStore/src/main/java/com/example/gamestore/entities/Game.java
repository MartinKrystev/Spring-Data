package com.example.gamestore.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static com.example.gamestore.constants.Validations.*;
import static com.example.gamestore.constants.Validations.INVALID_GAME_DESCRIPTION;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String trailer;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private float size;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String description;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    public Game() {
    }

    public Game(String title, String trailer, String imageUrl, float size, BigDecimal price, String description, LocalDate releaseDate) {
        this.setTitle(title);
        this.setTrailer(trailer);
        this.setImageUrl(imageUrl);
        this.setSize(size);
        this.setPrice(price);
        this.setDescription(description);
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!Character.isUpperCase(title.charAt(0))
                || title.length() <= 3
                || title.length() >= 100) {
            throw new IllegalArgumentException(INVALID_GAME_TITLE);
        }
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        if (trailer.length() != 11) {
            throw new IllegalArgumentException(INVALID_GAME_TRAILER);
        }
        this.trailer = trailer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        if (!imageUrl.startsWith("http://") && !imageUrl.startsWith("https://")) {
            throw new IllegalArgumentException(INVALID_GAME_THUMBNAIL);
        }
        this.imageUrl = imageUrl;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        if (size <= 0) {
            throw new IllegalArgumentException(INVALID_GAME_SIZE);
        }
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(INVALID_GAME_PRICE);
        }
        this.price = price;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() < 20) {
            throw new IllegalArgumentException(INVALID_GAME_DESCRIPTION);
        }
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Float.compare(game.size, size) == 0 && Objects.equals(title, game.title) && Objects.equals(trailer, game.trailer) && Objects.equals(imageUrl, game.imageUrl) && Objects.equals(price, game.price) && Objects.equals(description, game.description) && Objects.equals(releaseDate, game.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, trailer, imageUrl, size, price, description, releaseDate);
    }
}
