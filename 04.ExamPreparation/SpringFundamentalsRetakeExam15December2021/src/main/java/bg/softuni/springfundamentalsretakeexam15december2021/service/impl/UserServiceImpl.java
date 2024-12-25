package bg.softuni.springfundamentalsretakeexam15december2021.service.impl;

import bg.softuni.springfundamentalsretakeexam15december2021.model.dto.RegisterDTO;
import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.User;
import bg.softuni.springfundamentalsretakeexam15december2021.repository.UserRepository;
import bg.softuni.springfundamentalsretakeexam15december2021.service.UserService;
import bg.softuni.springfundamentalsretakeexam15december2021.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public void initAdmin() {
        if (userRepository.count() > 0) {
            return;
        }

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(this.passwordEncoder.encode("12345"));
        admin.setEmail("admin@abv.bg");
        this.userRepository.save(admin);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        User user = this.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        return this.passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username).orElse(null);
    }

    @Override
    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public void initUser() {
        if (this.userRepository.count() > 1) {
            return;
        }

        User user = new User();
        user.setUsername("User");
        user.setPassword(this.passwordEncoder.encode("12345"));
        user.setEmail("user@abv.bg");
        this.userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());

        this.userRepository.save(user);
    }
}
