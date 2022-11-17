package com.example.gamestore.services;

import com.example.gamestore.dtos.UserToLoginDTO;
import com.example.gamestore.dtos.UserToRegisterDTO;
import com.example.gamestore.entities.User;
import com.example.gamestore.repositories.GameRepository;
import com.example.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static com.example.gamestore.constants.Validations.*;

@Service
public class UserServiceImpl implements UserService {
    private User user;
    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.gameRepository = gameRepository;
//        this.userService = userService;
    }

    @Override
    public String registerUser(String[] args) {
        String email = args[1];
        String password = args[2];
        String confirmPassword = args[3];
        String fullName = args[4];

        UserToRegisterDTO userDTO;
        try {
            userDTO = new UserToRegisterDTO(email, password, confirmPassword, fullName);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        User user = this.mapper.map(userDTO, User.class);

        if (this.userRepository.count() == 0) {
            user.setAdmin(true);
        }

        boolean doesUserExist = this.userRepository.findByEmail(userDTO.getEmail()).isPresent();
        if (doesUserExist) {
            return EMAIL_ALREADY_REGISTERED;
        }

        this.userRepository.save(user);

        return userDTO.successfulRegister();
    }

    @Override
    public String loginUser(String[] data) {
        String email = data[1];
        String password = data[2];

        if (!Pattern.matches(EMAIL_REGEX, email) || !Pattern.matches(PASSWORD_REGEX, password)) {
            return INCORRECT_USER_OR_PASS;
        }

        UserToLoginDTO userToLoginDTO = new UserToLoginDTO(email, password);

        boolean doesUserExist = this.userRepository.findByEmail(userToLoginDTO.getEmail()).isPresent();

        User user1 = this.userRepository.findByEmail(userToLoginDTO.getEmail()).get();

        if (this.user == null && user1.getPassword().equals(userToLoginDTO.getPassword())) {
            this.user = this.userRepository.findByEmail(userToLoginDTO.getEmail()).get();
            return String.format(LOGIN_SUCCESSFUL, this.user.getFullName());
        } else if (this.user != null) {
            return String.format(USER_ALREADY_LOGGED_IN, this.user.getFullName());
        }
        return String.format(INCORRECT_USER_OR_PASS);
    }

    @Override
    public String logoutUser() {
        if (this.user == null) {
            return INCORRECT_LOGOUT;
        }

        String loggedOutOk = String.format(LOGOUT_SUCCESSFUL, this.user.getFullName());
        this.user = null;

        return loggedOutOk;
    }

    @Override
    public User getLoggedInUser() {
        return this.user;
    }

}
