package bg.softuni.coffeeshop.controller;

import bg.softuni.coffeeshop.model.dto.AddOrderDTO;
import bg.softuni.coffeeshop.service.OrderService;
import bg.softuni.coffeeshop.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final LoggedUser loggedUser;
    private final OrderService orderService;

    public OrderController(LoggedUser loggedUser,
                           OrderService orderService) {
        this.loggedUser = loggedUser;
        this.orderService = orderService;
    }

    @ModelAttribute
    public AddOrderDTO addOrderDTO() {
        return new AddOrderDTO();
    }

    @GetMapping("/add")
    public String add() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "order-add";
    }

    @PostMapping("/add")
    public String add(@Valid AddOrderDTO addOrderDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addOrderDTO", addOrderDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOrderDTO", result);

            return "redirect:/orders/add";
        }

        this.orderService.add(addOrderDTO, this.loggedUser.getId());

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        this.orderService.remove(id);
        return "redirect:/home";
    }
}
