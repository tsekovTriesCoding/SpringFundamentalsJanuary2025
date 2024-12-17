package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;

import java.util.Optional;

public interface UserService {
    boolean register(UserRegisterDTO data);

    boolean login(UserLoginDTO data);

    void logout();

    Optional<User> findById(long id);
}
