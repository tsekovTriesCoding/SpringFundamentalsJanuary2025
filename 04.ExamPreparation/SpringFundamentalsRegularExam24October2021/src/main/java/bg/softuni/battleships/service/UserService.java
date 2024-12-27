package bg.softuni.battleships.service;

import bg.softuni.battleships.model.entity.User;

public interface UserService {

    void initAdmin();

    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);
}
