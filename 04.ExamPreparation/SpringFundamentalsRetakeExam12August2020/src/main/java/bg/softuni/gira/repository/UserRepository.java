package bg.softuni.gira.repository;

import bg.softuni.gira.model.entity.User;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@ReadingConverter
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
