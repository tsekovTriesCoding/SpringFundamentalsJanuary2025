package com.likebookapp.service.impl;

import com.likebookapp.model.dto.PostDTO;
import com.likebookapp.model.dto.RegisterDTO;
import com.likebookapp.model.dto.UserWithPostsDTO;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
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
    public User findUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        this.userRepository.save(this.mapUser(registerDTO));
    }

    @Override
    public Optional<User> findUserById(long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Set<PostDTO> getUserPosts(Long id) {
        User user = this.userRepository.findById(id).orElse(null);

        return user.getPosts().stream().map(p -> {
            PostDTO postDTO = new PostDTO();

            postDTO
                    .setContent(p.getContent())
                    .setMood(p.getMood().getMoodName())
                    .setLikes(p.getUserLikes().size());
            return postDTO;
        }).collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public Set<UserWithPostsDTO> getOtherUsersPosts(Long id) {
        List<User> otherUsers = this.userRepository.findAllByIdIsNot(id);

        return otherUsers
                .stream()
                .map(u -> {
                    UserWithPostsDTO userWithPostsDTO = new UserWithPostsDTO();
                    Set<Post> currentUserPosts = u.getPosts();

                    Set<PostDTO> postsDTO = currentUserPosts.stream().map(p -> {
                        PostDTO postDTO = new PostDTO();

                        postDTO
                                .setContent(p.getContent())
                                .setMood(p.getMood().getMoodName())
                                .setLikes(p.getUserLikes().size());
                        return postDTO;
                    }).collect(Collectors.toSet());

                    userWithPostsDTO.setId(u.getId())
                            .setUsername(u.getUsername())
                            .setPosts(postsDTO);

                    return userWithPostsDTO;
                }).collect(Collectors.toSet());
    }

    @Override
    public void initTest() {
        if (this.userRepository.count() > 1) {
            return;
        }

        User user = new User();
        user.setUsername("user");
        user.setPassword(this.passwordEncoder.encode("12345"));
        user.setEmail("user@abv.bg");
        this.userRepository.save(user);
    }

    private User mapUser(RegisterDTO registerDTO) {
        User user = new User();

        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        return user;
    }

}
