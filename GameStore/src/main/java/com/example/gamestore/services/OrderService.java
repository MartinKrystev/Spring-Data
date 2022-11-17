package com.example.gamestore.services;

public interface OrderService {

    String removeGameFromCart(String gameTitle);
    String buyGamesFromCart();
}
