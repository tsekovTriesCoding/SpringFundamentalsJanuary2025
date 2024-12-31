package bg.softuni.gira.init;

import bg.softuni.gira.service.ClassificationService;
import bg.softuni.gira.service.TaskService;
import bg.softuni.gira.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
    private final ClassificationService classificationService;
    private final UserService userService;
    private final TaskService taskService;

    public FirstInit(ClassificationService classificationService,
                     UserService userService,
                     TaskService taskService) {
        this.classificationService = classificationService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.classificationService.initClassifications();
        this.userService.initAdmin();
        this.taskService.init();
    }
}
