package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.RegisterDTO;
import bg.softuni.battleships.model.dto.ShipShortInfoDTO;
import bg.softuni.battleships.model.entity.Ship;
import bg.softuni.battleships.model.entity.User;
import jakarta.validation.Valid;

import java.util.Set;

public interface UserService {

    void initAdmin();

    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

    User getUserByEmail(String value);

    void register(RegisterDTO registerDTO);

    Set<ShipShortInfoDTO> getUserShips(Long userId);

    void initUser();

    Set<ShipShortInfoDTO> getOtherUsersShips(Long userId);
}
