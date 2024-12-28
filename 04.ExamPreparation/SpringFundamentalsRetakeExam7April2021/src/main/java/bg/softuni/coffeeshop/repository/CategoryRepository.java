package bg.softuni.coffeeshop.repository;

import bg.softuni.coffeeshop.model.entity.Category;
import bg.softuni.coffeeshop.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryByName(CategoryEnum name);
}
