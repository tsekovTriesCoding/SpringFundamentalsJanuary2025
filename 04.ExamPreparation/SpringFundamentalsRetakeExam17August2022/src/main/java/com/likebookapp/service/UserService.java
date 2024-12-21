package com.likebookapp.service;

import com.likebookapp.model.entity.User;

public interface UserService {
    void initAdmin();

    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);
}
