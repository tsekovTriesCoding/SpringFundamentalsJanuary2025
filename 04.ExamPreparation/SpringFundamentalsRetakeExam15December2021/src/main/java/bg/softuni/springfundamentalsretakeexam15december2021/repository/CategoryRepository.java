package bg.softuni.springfundamentalsretakeexam15december2021.repository;

import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.Category;
import bg.softuni.springfundamentalsretakeexam15december2021.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getByName(CategoryEnum name);

}
