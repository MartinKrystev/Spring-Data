package com.example.gamestore.services;

import com.example.gamestore.entities.Game;

import java.math.BigDecimal;
import java.util.Optional;

public interface GameService {
    String addGame(String[] args);

    String editGame(String[] args);

    String deleteGame(long id);

    Optional<Game> findGameByTitleAndPrice(String title, BigDecimal price);

    Optional<Game> findGameById(long id);

    String gamesSimpleInfo();

    String gameDetailInfo(String datum);

    String gamesOwnedInfo();

    String addGameToCart(String gameTitle);
}
