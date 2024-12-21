package com.resellerapp.service.impl;

import com.resellerapp.model.dto.OfferDTO;
import com.resellerapp.model.dto.RegisterDTO;
import com.resellerapp.model.dto.UserWithOfferDTO;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public void initAdmin() {
        if (this.userRepository.count() > 0) {
            return;
        }

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(this.passwordEncoder.encode("12345"));
        admin.setEmail("admin@abv.bg");
        this.userRepository.save(admin);
    }

    @Override
    public void initTest() {
        if (this.userRepository.count() > 1) {
            return;
        }

        User test = new User();
        test.setUsername("testUser");
        test.setPassword(this.passwordEncoder.encode("12345"));
        test.setEmail("test@abv.bg");
        this.userRepository.save(test);
    }

    @Override
    public Optional<User> findUserById(long id) {
        return this.userRepository.findById(id);
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
    public User findUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        this.userRepository.save(this.mapUser(registerDTO));
    }

    @Override
    public List<Offer> getOffers(Long id) {
        User user = this.userRepository.findById(id).orElse(null);

        return user.getOffers();
    }

    @Override
    public List<Offer> getBoughtOffers(Long id) {
        User user = this.userRepository.findById(id).orElse(null);

        return user.getBoughtOffers();
    }

    @Override
    public List<UserWithOfferDTO> getAllOtherOffers(Long id) {
        List<User> otherUsers = this.userRepository.findAllByIdIsNot(id);

        return mapToAllOtherUsersOffersDTO(otherUsers);
    }

    private List<UserWithOfferDTO> mapToAllOtherUsersOffersDTO(List<User> otherUsers) {
        return otherUsers
                .stream()
                .map(e -> {
                    UserWithOfferDTO currentDTO = new UserWithOfferDTO();

                    List<Offer> offers = e.getOffers();
                    List<OfferDTO> offersDTO = offers
                            .stream()
                            .map(currentOffer -> {
                                OfferDTO offerDTO = new OfferDTO();

                                offerDTO.setId(currentOffer.getId())
                                        .setCondition(currentOffer.getCondition().getName())
                                        .setPrice(currentOffer.getPrice())
                                        .setDescription(currentOffer.getDescription());
                                return offerDTO;
                            }).collect(Collectors.toList());

                    currentDTO
                            .setId(e.getId())
                            .setUsername(e.getUsername())
                            .setOffers(offersDTO);
                    return currentDTO;
                })
                .collect(Collectors.toList());
    }

    private User mapUser(RegisterDTO registerDTO) {
        User user = new User();

        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        return user;
    }
}
