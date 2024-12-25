package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.RegisterDTO;
import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.model.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

    void initAdmin();

    User findUserByEmail(String value);

    void register(RegisterDTO registerDTO);

    Optional<User> findUserById(Long id);

    Set<SongDTO> getUserSongs(Long id);

    void initUser();

    void logout();
}
