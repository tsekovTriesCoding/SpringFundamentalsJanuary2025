package com.resellerapp.service;

import com.resellerapp.model.dto.RegisterDTO;
import com.resellerapp.model.dto.UserWithOfferDTO;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void initAdmin();

    void initTest();

    Optional<User> findUserById(long id);

    boolean checkCredentials(String username, String password);

    User getUserByUsername(String username);

    void login(String username);

    User findUserByEmail(String email);

    void register(RegisterDTO registerDTO);

    List<Offer> getOffers(Long id);

    List<Offer> getBoughtOffers(Long id);

    List<UserWithOfferDTO> getAllOtherOffers(Long id);
}
