package com.example.gamestore.dtos;

import java.util.regex.Pattern;

import static com.example.gamestore.constants.Validations.*;

public class UserToRegisterDTO {

    private String email;

    private String password;

    private String confirmPassword;

    private String fullName;

    public UserToRegisterDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        validate();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private void validate(){
        boolean isEmailValid = Pattern.matches(EMAIL_REGEX, this.email);
        if (!isEmailValid) {
            throw new IllegalArgumentException(INCORRECT_EMAIL);
        }

        boolean isPasswordValid = Pattern.matches(PASSWORD_REGEX, this.password);
        if (!isPasswordValid) {
            throw new IllegalArgumentException(INVALID_PASSWORD);
        }

        if (!this.password.equals(this.confirmPassword)) {
            throw new IllegalArgumentException(PASSWORDS_NO_MATCH);
        }
    }

    public String successfulRegister() {
        return String.format(REGISTER_SUCCESSFUL, this.fullName);
    }

}
