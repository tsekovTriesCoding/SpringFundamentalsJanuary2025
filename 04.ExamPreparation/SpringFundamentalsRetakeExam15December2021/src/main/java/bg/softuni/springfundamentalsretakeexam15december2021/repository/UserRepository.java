package bg.softuni.springfundamentalsretakeexam15december2021.repository;

import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
