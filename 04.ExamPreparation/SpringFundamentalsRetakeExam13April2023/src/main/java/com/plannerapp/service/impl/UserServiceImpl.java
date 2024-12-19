package com.plannerapp.service.impl;

import com.plannerapp.model.dto.RegisterDTO;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.UserService;
import com.plannerapp.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;
    private final HttpSession session;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           LoggedUser loggedUser,
                           HttpSession session) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.session = session;
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        this.userRepository.save(this.mapUser(registerDTO));
    }

    @Override
    public User findUserByUsername(String value) {
        return this.getUserByUsername(value);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        User user = this.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        return this.passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public void logout() {
        this.session.invalidate();
        this.loggedUser.logout();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public List<Task> getAssignedTasksFromCurrentUser(Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        return user.getAssignedTasks();
    }

    private User mapUser(RegisterDTO registerDTO) {
        User user = new User();

        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        return user;
    }

    private User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }
}
