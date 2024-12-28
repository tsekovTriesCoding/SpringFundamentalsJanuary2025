package bg.softuni.coffeeshop.service.impl;

import bg.softuni.coffeeshop.model.entity.User;
import bg.softuni.coffeeshop.model.dto.EmployeeDTO;
import bg.softuni.coffeeshop.model.dto.RegisterDTO;
import bg.softuni.coffeeshop.repository.UserRepository;
import bg.softuni.coffeeshop.service.UserService;
import bg.softuni.coffeeshop.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public boolean checkCredentials(String username, String password) {
        User user = this.getUserByUsername(username);

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
    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public void initAdmin() {
        if (userRepository.count() > 0) {
            return;
        }

        User admin = new User();
        admin.setUsername("admin");
        admin.setFirstName("Admin");
        admin.setLastName("Adminov");
        admin.setPassword(this.passwordEncoder.encode("12345"));
        admin.setEmail("admin@abv.bg");

        this.userRepository.save(admin);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        User user = new User();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());

        this.userRepository.save(user);
    }

    @Transactional
    @Override
    public Set<EmployeeDTO> getAllEmployees() {
        List<User> allEmployees = this.userRepository.findAll();
        return allEmployees.stream()
                .map(u -> {
                    EmployeeDTO employeeDTO = new EmployeeDTO();
                    employeeDTO.setFirstName(u.getFirstName());
                    employeeDTO.setLastName(u.getLastName());
                    employeeDTO.setNumberOfOrders(u.getOrders().size());
                    return employeeDTO;
                }).collect(Collectors.toSet());
    }

    @Override
    public void logout() {
        this.httpSession.invalidate();
        this.loggedUser.logout();
    }

}
