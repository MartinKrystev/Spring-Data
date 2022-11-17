package com.example.gamestore.repositories;

import com.example.gamestore.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findGameByTitleAndPrice(String title, BigDecimal price);
    Optional<Game> findGameById(long id);
    Optional<List<Game>> findAllByIdNotNull();
    Optional<Game> findGameByTitle(String gameTitle);
}
