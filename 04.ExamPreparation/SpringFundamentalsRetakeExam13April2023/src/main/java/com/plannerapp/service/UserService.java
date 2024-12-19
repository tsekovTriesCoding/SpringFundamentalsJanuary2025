package com.plannerapp.service;

import com.plannerapp.model.dto.RegisterDTO;
import com.plannerapp.model.entity.User;

public interface UserService {
    void register(RegisterDTO registerDTO);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    boolean checkCredentials(String username, String password);

    void login(String username);
}
