package com.example.gamestore.services;

import com.example.gamestore.entities.User;

public interface UserService {
    String registerUser(String[] args);

    String loginUser(String[] data);

    String logoutUser();

    User getLoggedInUser();

}
