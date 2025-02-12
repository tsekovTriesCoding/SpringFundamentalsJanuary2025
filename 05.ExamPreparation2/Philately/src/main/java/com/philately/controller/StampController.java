package com.philately.controller;

import com.philately.model.dto.CreateStampRequest;
import com.philately.model.entity.User;
import com.philately.service.StampService;
import com.philately.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/stamps")
public class StampController {
    private final UserService userService;
    private final StampService stampService;

    public StampController(UserService userService, StampService stampService) {
        this.userService = userService;
        this.stampService = stampService;
    }

    @GetMapping("/new")
    public ModelAndView getNewPainingPage(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-stamp");
        modelAndView.addObject("user", user);
        modelAndView.addObject("createStampRequest", new CreateStampRequest());

        return modelAndView;
    }

    @PostMapping("/new")
    public String createNewPainingPage(@Valid CreateStampRequest createStampRequest, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "add-stamp";
        }

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

       stampService.createNewStamp(createStampRequest, user);

        return "redirect:/home";
    }

    @GetMapping("/wished/{id}")
    public String addToWishedStamps(@PathVariable UUID id, HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        stampService.addToWishedStamps(id, user);

        return "redirect:/home";
    }

}