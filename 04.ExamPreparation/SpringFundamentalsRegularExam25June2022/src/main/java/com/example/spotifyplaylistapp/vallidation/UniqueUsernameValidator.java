package com.example.spotifyplaylistapp.vallidation;

import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.vallidation.annotation.UniqueUsername;
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