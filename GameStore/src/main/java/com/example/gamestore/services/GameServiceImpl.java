package com.example.gamestore.services;

import com.example.gamestore.dtos.GameDTO;
import com.example.gamestore.entities.Game;
import com.example.gamestore.entities.User;
import com.example.gamestore.repositories.GameRepository;
import com.example.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.gamestore.constants.Validations.*;

@Service
public class GameServiceImpl implements GameService {
    private User user;
    private Game game;
    private final UserService userService;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public GameServiceImpl(UserService userService, GameRepository gameRepository, UserRepository userRepository) {
        this.userService = userService;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String addGame(String[] args) {
        if (this.userService.getLoggedInUser() != null && this.userService.getLoggedInUser().isAdmin()) {
            String title = args[1];
            BigDecimal price = new BigDecimal(String.valueOf(args[2]));
            float size = Float.parseFloat(args[3]);
            String trailer = args[4];
            String imageURL = args[5];
            String description = args[6];

            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate releaseDate = LocalDate.parse(args[7], df);

            GameDTO gameDTO = new GameDTO(title, price, size, trailer, imageURL, description, releaseDate);

            Game gameToSave = this.mapper.map(gameDTO, Game.class);

            if (this.gameRepository.findGameByTitleAndPrice(gameToSave.getTitle(), gameToSave.getPrice()).isEmpty()) {
                this.gameRepository.save(gameToSave);
                return String.format(GAME_ADDED_MESSAGE, gameToSave.getTitle());

            } else {
                return String.format(INVALID_ADD_GAME, gameToSave.getTitle());
            }
        }
        return INVALID_COMMAND + " /" + USER_NOT_LOGGED_IN;
    }

    @Override
    public String editGame(String[] args) {
        long id = Long.parseLong(args[1]);

        if (findGameById(id).isEmpty()) {
            return INVALID_GAME_ID;
        } else {
            this.game = findGameById(id).orElseThrow();
        }

        this.user = this.userService.getLoggedInUser();
        if (this.user == null || !this.user.isAdmin()) {
            return INVALID_EDIT_OPERATION;
        }

        for (int i = 2; i < args.length; i++) {
            String[] data = args[i].split("=");
            String property = data[0];
            String propertyNewValue = data[1];

            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            switch (property) {
                case "title" -> game.setTitle(propertyNewValue);
                case "trailer" -> game.setTrailer(propertyNewValue);
                case "imageUrl" -> game.setImageUrl(propertyNewValue);
                case "size" -> game.setSize(Float.parseFloat(propertyNewValue));
                case "price" -> game.setPrice(new BigDecimal(propertyNewValue));
                case "description" -> game.setDescription(propertyNewValue);
                case "releaseDate" -> game.setReleaseDate(LocalDate.parse(propertyNewValue, df));
                default -> {
                }
            }
        }

        this.gameRepository.save(this.game);
        return String.format(EDIT_SUCCESSFUL, this.game.getTitle());
    }

    @Override
    //@Transient @Transactional not helping

    //TODO... Cannot delete or update a parent row: a foreign key constraint fails
    public String deleteGame(long id) {
        this.user = this.userService.getLoggedInUser();
        if (this.user == null || !this.user.isAdmin()) {
            return INVALID_EDIT_OPERATION;
        }

        if (findGameById(id).isEmpty()) {
            return INVALID_GAME_ID;
        } else {
            this.game = findGameById(id).orElseThrow();
        }


        this.gameRepository.delete(this.game);
        return String.format(DELETE_SUCCESSFUL, this.game.getTitle());
    }

    @Override
    public Optional<Game> findGameByTitleAndPrice(String title, BigDecimal price) {
        return this.gameRepository.findGameByTitleAndPrice(title, price);
    }

    @Override
    public Optional<Game> findGameById(long id) {
        return this.gameRepository.findGameById(id);
    }

    @Override
    public String gamesSimpleInfo() {
        List<Game> allGames = this.gameRepository.findAllByIdNotNull().orElse(null);

        if (allGames == null) {
            return INVALID_GAME_TITLE;
        }

        StringBuilder sb = new StringBuilder();

        allGames.forEach(g -> sb.append(g.getTitle()).append(" ").append(g.getPrice()).append(System.lineSeparator()));
        return sb.toString();
    }

    @Override
    public String gameDetailInfo(String gameTitle) {
        Game game1 = this.gameRepository.findGameByTitle(gameTitle).orElse(null);

        if (game1 == null) {
            return INVALID_GAME_TITLE;
        }

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(game1.getTitle()).append(System.lineSeparator());
        sb.append("Price: ").append(game1.getPrice()).append(System.lineSeparator());
        sb.append("Description: ").append(game1.getDescription()).append(System.lineSeparator());
        sb.append("Release date: ").append(df.format(game1.getReleaseDate()));

        return sb.toString();
    }

    @Override
    public String gamesOwnedInfo() {

        this.user = this.userService.getLoggedInUser();
        if (this.user == null) {
            return USER_NOT_LOGGED_IN;
        }

        Set<Game> orders = this.user.getOrders();
        if (orders.isEmpty()) {
            return String.format(USER_HAS_NO_GAMES, this.user.getFullName());
        }

        StringBuilder sb = new StringBuilder();
        orders.forEach(o -> {
            sb.append(o.getTitle()).append(System.lineSeparator());
        });

        return sb.toString();
    }

    @Override
    public String addGameToCart(String gameTitle) {
        this.user = this.userService.getLoggedInUser();
        if (this.user == null) {
            return USER_NOT_LOGGED_IN;
        }

        Game game = this.gameRepository.findGameByTitle(gameTitle).orElse(null);
        if (game == null) {
            return INVALID_GAME_TITLE;
        }

        if (this.user.getGames().contains(game)) {
            return String.format(GAME_ALREADY_ADDED, game.getTitle());
        }
        this.user.getGames().add(game);

        if (this.user.getOrders().contains(game)) {
            return String.format(GAME_ALREADY_PURCHASED, this.user.getFullName(), gameTitle);
        }

        this.userRepository.save(user);

        return String.format(GAME_ADDED_TO_CART, game.getTitle());
    }
}
