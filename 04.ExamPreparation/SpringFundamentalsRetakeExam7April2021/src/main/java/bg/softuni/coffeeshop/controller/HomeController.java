package bg.softuni.coffeeshop.controller;

import bg.softuni.coffeeshop.model.dto.EmployeeDTO;
import bg.softuni.coffeeshop.model.dto.OrderShortInfoDTO;
import bg.softuni.coffeeshop.service.OrderService;
import bg.softuni.coffeeshop.service.UserService;
import bg.softuni.coffeeshop.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final OrderService orderService;
    private final UserService userService;

    public HomeController(LoggedUser loggedUser,
                          OrderService orderService,
                          UserService userService) {
        this.loggedUser = loggedUser;
        this.orderService = orderService;
        this.userService = userService;
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

        Set<OrderShortInfoDTO> allOrders = this.orderService.getAll()
                .stream()
                .sorted(Comparator.comparing(OrderShortInfoDTO::getPrice).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
        model.addAttribute("allOrders", allOrders);

        Set<EmployeeDTO> employees = this.userService.getAllEmployees()
                .stream()
                .sorted(Comparator.comparingInt(EmployeeDTO::getNumberOfOrders).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
        model.addAttribute("employees", employees);

        return "home";
    }
}
