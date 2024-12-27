package bg.softuni.battleships.repository;

import bg.softuni.battleships.model.entity.Category;
import bg.softuni.battleships.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryByName(CategoryEnum name);
}
