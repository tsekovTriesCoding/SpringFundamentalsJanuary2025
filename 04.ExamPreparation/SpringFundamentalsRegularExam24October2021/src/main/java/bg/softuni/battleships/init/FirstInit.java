package bg.softuni.battleships.init;

import bg.softuni.battleships.service.CategoryService;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
    private final CategoryService categoryService;
    private final UserService userService;
    private final ShipService shipService;

    public FirstInit(CategoryService categoryService,
                     UserService userService,
                     ShipService shipService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.shipService = shipService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initCategories();
        this.userService.initAdmin();
        this.userService.initUser();
        this.shipService.initShips();
    }
}
