package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.LoggedUser;
import com.dictionaryapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder,
                           LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean register(UserRegisterDTO data) {
        if (!data.getPassword().equals(data.getConfirmPassword())) {
            return false;
        }

        boolean usernameOrEmailTaken = this.userRepository.existsByUsernameOrEmail(data.getUsername(), data.getEmail());

        if (usernameOrEmailTaken) {
            return false;
        }

        User user = this.modelMapper.map(data, User.class);
        user.setPassword(this.passwordEncoder.encode(data.getPassword()));

        this.userRepository.save(user);

        return true;
    }

    @Override
    public boolean login(UserLoginDTO data) {
        Optional<User> userByUsername = this.userRepository.findByUsername(data.getUsername());

        if (userByUsername.isEmpty()) {
            return false;
        }

        User user = userByUsername.get();

        if (!this.passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            return false;
        }

        this.loggedUser.login(user);

        return true;
    }

    @Override
    public void logout() {
        this.loggedUser.logout();
    }

    @Override
    public Optional<User> findById(long id) {
        return this.userRepository.findById(id);
    }
}
