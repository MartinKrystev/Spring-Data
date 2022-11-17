package com.example.gamestore.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.gamestore.constants.Validations.*;

public class GameDTO {
    private String title;
    private BigDecimal price;
    private float size;
    private String trailer;
    private String imageURL;
    private String description;
    private LocalDate releaseDate;

    public GameDTO(String title, BigDecimal price, float size, String trailer, String imageURL, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.imageURL = imageURL;
        this.description = description;
        this.releaseDate = releaseDate;
       // validateGame();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

//    public  void validateGame() {
//        if (!Character.isUpperCase(this.getTitle().charAt(0))
//                || this.getTitle().length() <= 3
//                || this.getTitle().length() >= 100) {
//            throw new IllegalArgumentException(INVALID_GAME_TITLE);
//        }
//
//        if (new BigDecimal(String.valueOf(this.getPrice())).compareTo(BigDecimal.ZERO) <= 0) {
//            throw new IllegalArgumentException(INVALID_GAME_PRICE);
//        }
//
//        if (this.getSize() <= 0) {
//            throw new IllegalArgumentException(INVALID_GAME_SIZE);
//        }
//
//        if (this.getTrailer().length() != 11) {
//            throw new IllegalArgumentException(INVALID_GAME_TRAILER);
//        }
//
//        if (!this.getImageURL().startsWith("http://") && !this.getImageURL().startsWith("https://")) {
//            throw new IllegalArgumentException(INVALID_GAME_THUMBNAIL);
//        }
//
//        if (this.getDescription().length() < 20) {
//            throw new IllegalArgumentException(INVALID_GAME_DESCRIPTION);
//        }
//    }
}
