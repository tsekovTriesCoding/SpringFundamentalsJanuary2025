package bg.softuni.musicdb.service;

import bg.softuni.musicdb.model.dto.RegisterDTO;
import bg.softuni.musicdb.model.entity.User;
import jakarta.validation.Valid;

public interface UserService {
    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

    void initAdmin();

    User getUserByEmail(String value);

    void register(RegisterDTO registerDTO);
}
