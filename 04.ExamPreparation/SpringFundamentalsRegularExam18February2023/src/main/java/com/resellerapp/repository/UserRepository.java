package com.resellerapp.repository;

import com.resellerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, LinkOption> {
    Optional<User> findById(long id);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    List<User> findAllByIdIsNot(Long id);

    Optional<User> findUserByOffers_Id(Long offerId);
}
