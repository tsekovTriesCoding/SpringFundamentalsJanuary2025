package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.dto.RegisterDTO;
import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository, LoggedUser loggedUser) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
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
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
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
    public User findUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());

        this.userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return this.userRepository.findById(id);
    }

    @Transactional
    @Override
    public Set<SongDTO> getUserSongs(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        return user.getPlaylist()
                .stream()
                .map(s -> {
                    SongDTO songDTO = new SongDTO();
                    songDTO.setId(s.getId());
                    songDTO.setTitle(s.getTitle());
                    songDTO.setDuration(s.getDuration());
                    songDTO.setPerformer(s.getPerformer());

                    return songDTO;
                })
                .collect(Collectors.toSet());
    }

    @Override
    public void initUser() {
        if (this.userRepository.count() > 1) {
            return;
        }

        User user = new User();
        user.setUsername("User");
        user.setPassword(this.passwordEncoder.encode("12345"));
        user.setEmail("user@abv.bg");
        this.userRepository.save(user);
    }
}
