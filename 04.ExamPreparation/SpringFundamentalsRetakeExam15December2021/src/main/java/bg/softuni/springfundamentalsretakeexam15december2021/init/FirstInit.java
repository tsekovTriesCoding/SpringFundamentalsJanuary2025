package bg.softuni.springfundamentalsretakeexam15december2021.init;

import bg.softuni.springfundamentalsretakeexam15december2021.service.CategoryService;
import bg.softuni.springfundamentalsretakeexam15december2021.service.ProductService;
import bg.softuni.springfundamentalsretakeexam15december2021.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
   private final CategoryService categoryService;
   private final UserService userService;
   private final ProductService productService;

    public FirstInit(CategoryService categoryService,
                     UserService userService,
                     ProductService productService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
      this.categoryService.initCategories();
        this.userService.initAdmin();
        this.userService.initUser();
        this.productService.initProducts();
    }
}
