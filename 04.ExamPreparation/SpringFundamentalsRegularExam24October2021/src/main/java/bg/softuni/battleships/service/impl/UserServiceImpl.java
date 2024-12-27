package bg.softuni.battleships.service.impl;

import bg.softuni.battleships.model.dto.RegisterDTO;
import bg.softuni.battleships.model.dto.ShipShortInfoDTO;
import bg.softuni.battleships.model.entity.Ship;
import bg.softuni.battleships.model.entity.User;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.service.UserService;
import bg.softuni.battleships.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        admin.setFullName("Admin Adminov");
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
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFullName());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());

        this.userRepository.save(user);
    }

    @Override
    public Set<ShipShortInfoDTO> getUserShips(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        return user.getShips()
                .stream()
                .map(s -> {
                    ShipShortInfoDTO shipShortInfoDTO = new ShipShortInfoDTO();
                    shipShortInfoDTO.setName(s.getName());
                    return shipShortInfoDTO;
                }).collect(Collectors.toSet());
    }

    @Override
    public void initUser() {
        if (userRepository.count() > 1) {
            return;
        }

        User admin = new User();
        admin.setUsername("user");
        admin.setFullName("User Userov");
        admin.setPassword(this.passwordEncoder.encode("12345"));
        admin.setEmail("user@abv.bg");
        this.userRepository.save(admin);
    }

    @Override
    public Set<ShipShortInfoDTO> getOtherUsersShips(Long userId) {
        List<User> allOtherUsers = this.userRepository.getAllByIdNot(userId);

        Set<ShipShortInfoDTO> otherUsersShips = new HashSet<>();

        allOtherUsers.forEach(u -> {
            Set<Ship> ships = u.getShips();
            Set<ShipShortInfoDTO> collect = ships.stream().map(s -> {
                ShipShortInfoDTO shipShortInfoDTO = new ShipShortInfoDTO();
                shipShortInfoDTO.setName(s.getName());
                return shipShortInfoDTO;
            }).collect(Collectors.toSet());

            otherUsersShips.addAll(collect);
        });

        return otherUsersShips;
    }
}
