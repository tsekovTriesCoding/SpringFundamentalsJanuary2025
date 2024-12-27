package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.User;

public interface UserService {
    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

    void initAdmin();
}
