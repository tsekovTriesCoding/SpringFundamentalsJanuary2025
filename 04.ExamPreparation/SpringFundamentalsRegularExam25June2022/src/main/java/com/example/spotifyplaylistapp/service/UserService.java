package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.User;

public interface UserService {
    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);
}
