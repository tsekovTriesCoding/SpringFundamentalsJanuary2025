package bg.softuni.gira.vallidation;

import bg.softuni.gira.service.TaskService;
import bg.softuni.gira.vallidation.annotation.UniqueTask;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueTaskValidator implements ConstraintValidator<UniqueTask, String> {
    private final TaskService taskService;

    public UniqueTaskValidator(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.taskService.getByName(value) == null;
    }
}
