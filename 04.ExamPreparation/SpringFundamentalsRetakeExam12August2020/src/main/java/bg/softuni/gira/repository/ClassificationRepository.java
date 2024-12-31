package bg.softuni.gira.repository;

import bg.softuni.gira.model.entity.Classification;
import bg.softuni.gira.model.enums.ClassificationEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Optional<Classification> findByName(ClassificationEnum name);
}
