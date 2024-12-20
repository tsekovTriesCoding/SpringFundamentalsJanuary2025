package com.plannerapp.service;

import com.plannerapp.model.dto.RegisterDTO;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void register(RegisterDTO registerDTO);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    boolean checkCredentials(String username, String password);

    void login(String username);

    void logout();

    Optional<User> findUserById(Long id);

    List<Task> getAssignedTasksFromCurrentUser(Long id);
}
