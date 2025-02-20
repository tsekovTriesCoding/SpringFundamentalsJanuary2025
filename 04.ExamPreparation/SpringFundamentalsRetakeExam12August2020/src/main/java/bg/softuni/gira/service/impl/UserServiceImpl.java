package bg.softuni.gira.service.impl;

import bg.softuni.gira.model.dto.RegisterDTO;
import bg.softuni.gira.model.entity.User;
import bg.softuni.gira.repository.UserRepository;
import bg.softuni.gira.service.UserService;
import bg.softuni.gira.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;
    private final HttpSession httpSession;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           LoggedUser loggedUser,
                           HttpSession httpSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.httpSession = httpSession;
    }

    @Override
    public boolean checkCredentials(String email, String password) {
        User user = this.getUserByEmail(email);

        if (user == null) {
            return false;
        }

        return this.passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void login(String email) {
        User user = this.getUserByEmail(email);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
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
    public void register(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());

        this.userRepository.save(user);
    }

    @Override
    public void logout() {
        this.httpSession.invalidate();
        this.loggedUser.logout();
    }
}
