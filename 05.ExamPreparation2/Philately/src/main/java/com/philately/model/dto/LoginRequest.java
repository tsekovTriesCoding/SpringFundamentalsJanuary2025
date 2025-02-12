package com.philately.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    @NotNull(message = "Not null username!")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotNull(message = "Not null password!")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    public LoginRequest() {
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginRequest setUsername(String username) {
        this.username = username;
        return this;
    }
}
