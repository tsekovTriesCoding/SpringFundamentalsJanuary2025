package com.plannerapp.controller;

import com.plannerapp.model.dto.LoginDTO;
import com.plannerapp.model.dto.RegisterDTO;
import com.plannerapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserControllerImpl implements UserController {
    private final UserServiceImpl userService;

    @ModelAttribute
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }

    @Autowired
    public UserControllerImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public String login(Model model) {
        return "login";
    }

    @Override
    public String loginConfirm(LoginDTO loginDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        return null;
    }

    @Override
    public String register() {
        return "register";
    }

    @Override
    public String registerConfirm(RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmPassword",
                            "Passwords must be the same."));
        }

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", result);

            return "redirect:/users/register";
        }

        this.userService.register(registerDTO);
        return "redirect:/home";
    }

    @Override
    public String logout() {
        return null;
    }
}
