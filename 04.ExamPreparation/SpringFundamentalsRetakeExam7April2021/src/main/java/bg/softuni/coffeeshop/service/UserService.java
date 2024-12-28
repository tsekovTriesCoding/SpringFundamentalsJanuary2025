package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.entity.User;
import bg.softuni.coffeeshop.model.dto.EmployeeDTO;
import bg.softuni.coffeeshop.model.dto.RegisterDTO;

import java.util.Set;

public interface UserService {
    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

    void initAdmin();

    User getUserByEmail(String value);

    void register(RegisterDTO registerDTO);

    Set<EmployeeDTO> getAllEmployees();
}
