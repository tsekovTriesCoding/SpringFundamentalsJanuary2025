package bg.softuni.springfundamentalsretakeexam15december2021.repository;

import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.Category;
import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllByCategory(Category category);
}
