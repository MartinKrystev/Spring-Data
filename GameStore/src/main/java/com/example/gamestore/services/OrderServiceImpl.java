package com.example.gamestore.services;

//import com.example.gamestore.entities.Order;

import com.example.gamestore.entities.Game;
import com.example.gamestore.entities.User;
import com.example.gamestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.example.gamestore.constants.Validations.*;

@Service
public class OrderServiceImpl implements OrderService {
    private User user;

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(UserService userService, UserRepository userRepository) {

        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public String removeGameFromCart(String gameTitle) {
        this.user = this.userService.getLoggedInUser();
        if (this.user == null) {
            return USER_NOT_LOGGED_IN;
        }

        if (this.user.getGames().isEmpty()) {
            return EMPTY_CART;
        }

        Game game = this.user.getGames()
                .stream()
                .filter(g -> g.getTitle().equals(gameTitle))
                .findFirst()
                .orElse(null);

        if (game == null) {
            return String.format(NO_SUCH_GAME_IN_CART, gameTitle);
        } else {
            this.user.getGames().remove(game);
        }

        this.userRepository.save(user);
        return String.format(REMOVE_FROM_CART_SUCCESSFUL, gameTitle);
    }

    @Override
    public String buyGamesFromCart() {

        this.user = this.userService.getLoggedInUser();
        if (this.user == null) {
            return USER_NOT_LOGGED_IN;
        }

        Set<Game> games = this.user.getGames();
        if (games.isEmpty()) {
            return EMPTY_CART;
        }

        Set<Game> ownedGames = this.user.getOrders();
        Set<Game> gamesToBuy = this.user.getGames();

        for (Game g : gamesToBuy) {
            for (Game owned : ownedGames) {
                if (g.getTitle().equals(owned.getTitle())) {
                    gamesToBuy.remove(g);
                }
            }
        }

        if (gamesToBuy.isEmpty()) {
            return EMPTY_CART;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Successfully bought games:").append(System.lineSeparator());
        gamesToBuy.forEach(g -> {
            sb.append(" -").append(g.getTitle()).append(System.lineSeparator());
            this.user.getOrders().add(g);
        });

        this.user.getGames().clear();

        this.userRepository.save(this.user);
        return sb.toString();
    }
}
