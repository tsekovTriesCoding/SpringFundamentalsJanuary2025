package com.likebookapp.service;

import com.likebookapp.model.dto.RegisterDTO;
import com.likebookapp.model.entity.User;

import java.util.Optional;

public interface UserService {
    void initAdmin();

    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

   User findUserByEmail(String email);

    void register(RegisterDTO registerDTO);
}
