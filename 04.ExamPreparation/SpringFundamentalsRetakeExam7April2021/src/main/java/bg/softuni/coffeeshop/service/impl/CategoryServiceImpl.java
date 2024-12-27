package bg.softuni.coffeeshop.service.impl;

import bg.softuni.coffeeshop.model.Category;
import bg.softuni.coffeeshop.model.enums.CategoryEnum;
import bg.softuni.coffeeshop.repository.CategoryRepository;
import bg.softuni.coffeeshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final Map<CategoryEnum, Integer> categoriesTimes = Map.of(
            CategoryEnum.DRINK, 1,
            CategoryEnum.COFFEE, 2,
            CategoryEnum.OTHER, 5,
            CategoryEnum.CAKE, 10);

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() > 0) {
            return;
        }

        categoriesTimes.forEach((categoryEnum, time) -> {
            Category category = new Category(categoryEnum, time);
            this.categoryRepository.saveAndFlush(category);
        });
    }
}
