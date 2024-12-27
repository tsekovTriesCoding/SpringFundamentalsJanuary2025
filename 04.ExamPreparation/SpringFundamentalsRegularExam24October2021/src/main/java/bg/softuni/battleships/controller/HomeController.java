package bg.softuni.battleships.controller;

import bg.softuni.battleships.model.dto.ShipShortInfoDTO;
import bg.softuni.battleships.model.entity.Ship;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.service.UserService;
import bg.softuni.battleships.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final ShipService shipService;

    public HomeController(LoggedUser loggedUser,
                          UserService userService,
                          ShipService shipService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.shipService = shipService;
    }

    @GetMapping
    String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("home")
    String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        Set<ShipShortInfoDTO> currentUserShips = this.userService.getUserShips(this.loggedUser.getId());
        model.addAttribute("currentUserShips", currentUserShips);

        Set<ShipShortInfoDTO> otherUsersShips = this.userService.getOtherUsersShips(this.loggedUser.getId());
        model.addAttribute("otherUsersShips", otherUsersShips);

        Set<ShipShortInfoDTO> allShips = this.shipService.getAllShips()
                .stream()
                .sorted(Comparator.comparing(ShipShortInfoDTO::getName)
                .thenComparingLong(ShipShortInfoDTO::getHealth)
                .thenComparingLong(ShipShortInfoDTO::getPower))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        model.addAttribute("allShips", allShips);

        return "home";
    }
}
