package bg.softuni.springfundamentalsretakeexam15december2021.vallidation;

import bg.softuni.springfundamentalsretakeexam15december2021.service.UserService;
import bg.softuni.springfundamentalsretakeexam15december2021.vallidation.annotation.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userService.getUserByUsername(value) == null;
    }
}