package bg.softuni.battleships.service.impl;

import bg.softuni.battleships.model.entity.Category;
import bg.softuni.battleships.model.enums.CategoryEnum;
import bg.softuni.battleships.repository.CategoryRepository;
import bg.softuni.battleships.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() > 0) {
            return;
        }

        for (CategoryEnum name : CategoryEnum.values()) {
            Category category = new Category(name);

            this.categoryRepository.save(category);
        }
    }
}
