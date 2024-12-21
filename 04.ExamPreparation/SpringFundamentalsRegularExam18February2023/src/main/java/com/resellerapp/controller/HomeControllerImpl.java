package com.resellerapp.controller;

import com.resellerapp.model.dto.UserWithOfferDTO;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.service.impl.OfferServiceImpl;
import com.resellerapp.service.impl.UserServiceImpl;
import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.IntStream;

@Controller
public class HomeControllerImpl implements HomeController {
    private final LoggedUser loggedUser;
    private final UserServiceImpl userService;
    private final OfferServiceImpl offerService;

    @Autowired
    public HomeControllerImpl(LoggedUser loggedUser, UserServiceImpl userService, OfferServiceImpl offerService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.offerService = offerService;
    }

    @Override
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @Override
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = this.userService.findUserById(loggedUser.getId()).orElse(null);
        model.addAttribute("currentUserInfo", user);

        List<Offer> currentUserOffers = this.userService.getOffers(this.loggedUser.getId());
        model.addAttribute("userOffers", currentUserOffers);

        List<Offer> currentUserBoughtOffers = this.userService.getBoughtOffers(this.loggedUser.getId());
        model.addAttribute("userBoughtOffers", currentUserBoughtOffers);

        List<UserWithOfferDTO> allOtherOffers = this.userService.getAllOtherOffers(this.loggedUser.getId());
        model.addAttribute("allOtherOffers", allOtherOffers);

        int totalOffers = allOtherOffers
                .stream()
                .flatMapToInt(o -> IntStream.of(o.getOffers().size())).sum();
        model.addAttribute("totalOffers", totalOffers);

        return "home";
    }
}
