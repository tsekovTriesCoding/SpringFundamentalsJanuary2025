package bg.softuni.musicdb.service;

import bg.softuni.musicdb.model.entity.User;

public interface UserService {
    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

    void initAdmin();
}
