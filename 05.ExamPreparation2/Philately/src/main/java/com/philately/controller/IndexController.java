package com.philately.controller;

import com.philately.model.dto.LoginRequest;
import com.philately.model.dto.RegisterRequest;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.model.entity.WishedStamp;
import com.philately.service.StampService;
import com.philately.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class IndexController {

    private final UserService userService;
    private final StampService stampService;

    public IndexController(UserService userService, StampService stampService) {
        this.userService = userService;
        this.stampService = stampService;
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView getHomePage(HttpSession session) {
        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        ModelAndView modelAndView = new ModelAndView();
        List<Stamp> allOtherUsersStamps = stampService.getAllOtherUsersStamps(userId);

        modelAndView.setViewName("home");
        modelAndView.addObject("user", user);
        modelAndView.addObject("myStamps", user.getStamps());
        modelAndView.addObject("otherStamps", allOtherUsersStamps);
        modelAndView.addObject("wishedStamps", user.getWishedStamps());

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("registerRequest", new RegisterRequest());

        return modelAndView;
    }

    @PostMapping("/register")
    public String processRegisterRequest(@Valid RegisterRequest registerRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.registerUser(registerRequest);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("loginRequest", new LoginRequest());

        return modelAndView;
    }

    @PostMapping("/login")
    public String processLoginRequest(@Valid LoginRequest loginRequest, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        User user = userService.loginUser(loginRequest);

        session.setAttribute("user_id", user.getId());

        return "redirect:/home";
    }
}
