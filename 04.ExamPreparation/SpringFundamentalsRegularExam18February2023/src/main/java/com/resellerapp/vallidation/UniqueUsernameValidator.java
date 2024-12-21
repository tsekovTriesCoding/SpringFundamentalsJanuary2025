package com.resellerapp.vallidation;

import com.plannerapp.service.impl.UserServiceImpl;
import com.plannerapp.vallidation.annotation.UniqueUsername;
import com.resellerapp.service.impl.UserServiceImpl;
import com.resellerapp.vallidation.annotation.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserServiceImpl userService;

    public UniqueUsernameValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userService.getUserByUsername(value) == null;
    }
}