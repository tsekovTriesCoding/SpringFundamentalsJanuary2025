package bg.softuni.springfundamentalsretakeexam15december2021.init;

import bg.softuni.springfundamentalsretakeexam15december2021.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
   private final CategoryService categoryService;

    public FirstInit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
      this.categoryService.initCategories();
    }
}
