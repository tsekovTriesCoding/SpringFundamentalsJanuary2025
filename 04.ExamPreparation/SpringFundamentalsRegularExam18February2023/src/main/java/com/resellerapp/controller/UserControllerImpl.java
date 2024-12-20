package com.resellerapp.controller;

import com.resellerapp.model.dto.LoginDTO;
import com.resellerapp.model.dto.RegisterDTO;
import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserControllerImpl implements UserController {
    private final LoggedUser loggedUser;

    @Autowired
    public UserControllerImpl(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public String login(Model model) {
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "login";
    }

    @Override
    public String loginConfirm(LoginDTO loginDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        return null;
    }

    @Override
    public String register() {
        return null;
    }

    @Override
    public String registerConfirm(RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        return null;
    }

    @Override
    public String logout() {
        return null;
    }
}
