package bg.softuni.gira.init;

import bg.softuni.gira.service.ClassificationService;
import bg.softuni.gira.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
  private final ClassificationService classificationService;
  private final UserService userService;

    public FirstInit(ClassificationService classificationService,
                     UserService userService) {
        this.classificationService = classificationService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
      this.classificationService.initClassifications();
      this.userService.initAdmin();
    }
}
