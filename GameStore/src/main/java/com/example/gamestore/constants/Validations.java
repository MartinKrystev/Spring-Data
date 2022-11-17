package com.example.gamestore.constants;

public enum Validations {
    ;
    public static final String EMAIL_REGEX = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
    public static final String INCORRECT_EMAIL = "Incorrect email.";
    public static final String PASSWORD_REGEX = "(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    public static final String INVALID_PASSWORD = "Invalid password.";
    public static final String PASSWORDS_NO_MATCH = "Passwords do NOT match.";
    public static final String COMMAND_NOT_FOUND = "Command NOT found.";
    public static final String LOGIN_SUCCESSFUL = "Successfully logged in %s";
    public static final String LOGOUT_SUCCESSFUL = "User %s successfully logged out";
    public static final String USER_ALREADY_LOGGED_IN = "User %s is already logged in.";
    public static final String INCORRECT_LOGOUT = "Cannot log out. No user was logged in.";
    public static final String REGISTER_SUCCESSFUL = "%s was registered.";
    public static final String EMAIL_ALREADY_REGISTERED = "Email is already registered.";
    public static final String INCORRECT_USER_OR_PASS = "Incorrect username / password";

    public static final String INVALID_GAME_TITLE = "Invalid game title.";
    public static final String INVALID_GAME_PRICE = "Invalid game price. The price should be a positive number.";
    public static final String INVALID_GAME_SIZE = "Invalid game size. The size should be a positive number.";
    public static final String INVALID_GAME_TRAILER = "Invalid game trailer. The trailer length should be 11 characters.";
    public static final String INVALID_GAME_THUMBNAIL = "Invalid game URL. The URL should start with http:// or https://.";
    public static final String INVALID_GAME_DESCRIPTION = "Invalid game description. The description should be at least 20 characters.";

    public static final String GAME_ADDED_MESSAGE = "Added %s";
    public static final String INVALID_COMMAND = "Invalid command.";
    public static final String INVALID_ADD_GAME = "Game %s is already added.";

    public static final String INVALID_GAME_ID = "Invalid game id.";
    public static final String INVALID_EDIT_OPERATION = "Invalid operation. Only administrator can edit games.";
    public static final String EDIT_SUCCESSFUL = "Edited %s";
    public static final String DELETE_SUCCESSFUL = "Deleted %s";


    public static final String USER_NOT_LOGGED_IN = "Invalid operation. Please log in to continue.";
    public static final String GAME_ADDED_TO_CART = "%s added to cart.";
    public static final String GAME_ALREADY_ADDED = "%s is already added to cart.";
    public static final String EMPTY_CART = "No items in the cart.";
    public static final String NO_SUCH_GAME_IN_CART = "%s is not added in the cart.";
    public static final String REMOVE_FROM_CART_SUCCESSFUL = "%s removed from cart.";

    public static final String USER_HAS_NO_GAMES = "%s has no purchased games.";
    public static final String GAME_ALREADY_PURCHASED = "%s already has -%s- purchased.";

}
