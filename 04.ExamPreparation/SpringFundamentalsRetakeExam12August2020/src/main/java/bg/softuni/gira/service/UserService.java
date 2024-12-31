package bg.softuni.gira.service;

import bg.softuni.gira.model.dto.RegisterDTO;
import bg.softuni.gira.model.entity.User;

public interface UserService {
    boolean checkCredentials(String email, String password);

    User getUserByUsername(String username);

    void login(String email);

    User getUserByEmail(String email);

    void initAdmin();

    void register(RegisterDTO registerDTO);

    void logout();
}
