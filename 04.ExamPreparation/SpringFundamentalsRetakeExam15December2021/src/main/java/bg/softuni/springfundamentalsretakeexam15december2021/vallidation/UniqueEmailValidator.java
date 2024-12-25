package bg.softuni.springfundamentalsretakeexam15december2021.vallidation;

import bg.softuni.springfundamentalsretakeexam15december2021.service.UserService;
import bg.softuni.springfundamentalsretakeexam15december2021.vallidation.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userService.getUserByEmail(value) == null;
    }
}