package bg.softuni.springfundamentalsretakeexam15december2021.service;

import bg.softuni.springfundamentalsretakeexam15december2021.model.dto.RegisterDTO;
import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.User;

public interface UserService {
    void initAdmin();

    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

    void initUser();

    User getUserByEmail(String email);

    void register(RegisterDTO registerDTO);
}
