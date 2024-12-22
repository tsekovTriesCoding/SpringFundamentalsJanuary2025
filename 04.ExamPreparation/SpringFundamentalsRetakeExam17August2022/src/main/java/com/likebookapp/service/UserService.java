package com.likebookapp.service;

import com.likebookapp.model.dto.PostDTO;
import com.likebookapp.model.dto.RegisterDTO;
import com.likebookapp.model.dto.UserWithPostsDTO;
import com.likebookapp.model.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    void initAdmin();

    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

    User findUserByEmail(String email);

    void register(RegisterDTO registerDTO);

    Optional<User> findUserById(long id);

    Set<PostDTO> getUserPosts(Long id);

    Set<UserWithPostsDTO> getOtherUsersPosts(Long id);

    void initTest();
}
