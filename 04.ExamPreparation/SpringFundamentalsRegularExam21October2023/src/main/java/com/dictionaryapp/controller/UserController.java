package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(name = "registerData")
    public UserRegisterDTO registerData() {
        return new UserRegisterDTO();
    }

    @ModelAttribute(name = "loginData")
    public UserLoginDTO loginData() {
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public String viewRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO data,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:/register";
        }

        this.userService.register(data);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String viewLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid UserLoginDTO data,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);

            return "redirect:/login";
        }

        boolean success = this.userService.login(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute("userPassMismatch", true);

            return "redirect:/login";
        }


        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logout();
        return "redirect:/";
    }
}
