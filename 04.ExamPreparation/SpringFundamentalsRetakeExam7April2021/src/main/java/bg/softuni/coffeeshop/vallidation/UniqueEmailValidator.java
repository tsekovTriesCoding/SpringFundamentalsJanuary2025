package bg.softuni.coffeeshop.vallidation;

import bg.softuni.coffeeshop.service.UserService;
import bg.softuni.coffeeshop.vallidation.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final bg.softuni.coffeeshop.service.UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userService.getUserByEmail(value) == null;
    }
}