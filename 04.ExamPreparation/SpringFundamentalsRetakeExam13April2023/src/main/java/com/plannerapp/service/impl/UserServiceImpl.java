package com.plannerapp.service.impl;

import com.plannerapp.model.dto.RegisterDTO;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        this.userRepository.save(this.mapUser(registerDTO));
    }

    private User mapUser(RegisterDTO registerDTO) {
        User user = new User();

        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        return user;
    }
}
