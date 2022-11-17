package com.example.gamestore;

import com.example.gamestore.services.GameService;
import com.example.gamestore.services.OrderService;
import com.example.gamestore.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.example.gamestore.constants.Commands.*;
import static com.example.gamestore.constants.Validations.COMMAND_NOT_FOUND;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private static final Scanner scanner = new Scanner(System.in);
    private final UserService userService;
    private final GameService gameService;
    private final OrderService orderService;

    public ConsoleRunner(UserService userService, GameService gameService, OrderService orderService) {
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
    }

    //Commands to test could be found in "resources.game_store_commands.txt"

    @Override
    public void run(String... args) throws Exception {
        String input = scanner.nextLine();

        while (!input.equals("Exit")) {
            String[] data = input.split("\\|");
            String command = data[0];

            String output = switch (command) {
                case REGISTER_USER -> this.userService.registerUser(data);
                case LOGIN_USER -> this.userService.loginUser(data);
                case LOGOUT -> this.userService.logoutUser();
                case ADD_GAME -> this.gameService.addGame(data);
                case EDIT_GAME -> this.gameService.editGame(data);
                case DELETE_GAME -> this.gameService.deleteGame(Long.parseLong(data[1]));
                case ALL_GAMES -> this.gameService.gamesSimpleInfo();
                case DETAIL_GAME -> this.gameService.gameDetailInfo(data[1]);
                case OWNED_GAMES -> this.gameService.gamesOwnedInfo();
                case ADD_ITEM -> this.gameService.addGameToCart(data[1]);
                case REMOVE_ITEM -> this.orderService.removeGameFromCart(data[1]);
                case BUY_ITEM -> this.orderService.buyGamesFromCart();
                default -> COMMAND_NOT_FOUND;
            };
            System.out.println(output);
            input = scanner.nextLine();

        }


    }
}
