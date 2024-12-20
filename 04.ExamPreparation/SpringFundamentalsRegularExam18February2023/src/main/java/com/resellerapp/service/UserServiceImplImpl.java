package com.resellerapp.service;

import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplImpl implements UserServiceImpl {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initAdmin() {
        if (this.userRepository.count() > 0) {
            return;
        }

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(this.passwordEncoder.encode("12345"));
        admin.setEmail("admin@abv.bg");
        this.userRepository.save(admin);
    }

    @Override
    public void initTest() {
        if (this.userRepository.count() > 0) {
            return;
        }

        User test = new User();
        test.setUsername("testUser");
        test.setPassword(this.passwordEncoder.encode("12345"));
        test.setEmail("test@abv.bg");
        this.userRepository.save(test);
    }

    @Override
    public Optional<User> findUserById(long id) {
        return this.userRepository.findById(id);
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
    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username).orElse(null);
    }

    @Override
    public void login(String username) {

    }
}
