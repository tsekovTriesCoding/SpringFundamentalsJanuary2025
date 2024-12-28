package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.User;
import bg.softuni.coffeeshop.model.dto.RegisterDTO;

public interface UserService {
    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

    void initAdmin();

    User getUserByEmail(String value);

    void register(RegisterDTO registerDTO);
}
