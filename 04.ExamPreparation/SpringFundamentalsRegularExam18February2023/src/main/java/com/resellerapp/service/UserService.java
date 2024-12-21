package com.resellerapp.service;

import com.resellerapp.model.entity.User;

import java.util.Optional;

public interface UserService {
    void initAdmin();

    void initTest();

    Optional<User> findUserById(long id);

    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

    User findUserByEmail(String email);
}
