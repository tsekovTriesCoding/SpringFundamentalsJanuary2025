package bg.softuni.coffeeshop.init;

import bg.softuni.coffeeshop.service.CategoryService;
import bg.softuni.coffeeshop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
    private final CategoryService categoryService;
    private final UserService userService;

    public FirstInit(CategoryService categoryService,
                     UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initCategories();
        this.userService.initAdmin();
    }
}
