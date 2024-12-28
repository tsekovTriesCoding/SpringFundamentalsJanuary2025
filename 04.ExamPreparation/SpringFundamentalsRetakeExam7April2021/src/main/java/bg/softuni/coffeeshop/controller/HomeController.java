package bg.softuni.coffeeshop.controller;

import bg.softuni.coffeeshop.model.Order;
import bg.softuni.coffeeshop.service.OrderService;
import bg.softuni.coffeeshop.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final OrderService orderService;

    public HomeController(LoggedUser loggedUser,
                          OrderService orderService) {
        this.loggedUser = loggedUser;
        this.orderService = orderService;
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

        List<Order> allOrders = this.orderService.getAll()
                .stream()
                .sorted(Comparator.comparing(Order::getPrice).reversed())
                .toList();
        model.addAttribute("allOrders", allOrders);

        Set<Order> allOtherOrders = this.orderService.getAllOtherOrders();
        model.addAttribute("allOtherOrders", allOtherOrders);

        Set<Order> allCoffeeOrders = this.orderService.getAllCoffeeOrders();
        model.addAttribute("allCoffeeOrders", allCoffeeOrders);

        return "home";
    }
}
