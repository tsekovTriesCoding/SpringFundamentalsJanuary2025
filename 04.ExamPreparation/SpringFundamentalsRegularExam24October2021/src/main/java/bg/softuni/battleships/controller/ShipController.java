package bg.softuni.battleships.controller;

import bg.softuni.battleships.model.dto.AddShipDTO;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ships")
public class ShipController {
    private final LoggedUser loggedUser;
    private final ShipService shipService;

    public ShipController(LoggedUser loggedUser, ShipService shipService) {
        this.loggedUser = loggedUser;
        this.shipService = shipService;
    }

    @ModelAttribute
    public AddShipDTO getAddShipDTO() {
        return new AddShipDTO();
    }

    @GetMapping("/add")
    public String addShip() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "ship-add";
    }

    @PostMapping("/add")
    public String addShip(@Valid AddShipDTO addShipDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addShipDTO", addShipDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addShipDTO", result);

            return "redirect:/ships/add";
        }

        this.shipService.addShip(addShipDTO, this.loggedUser.getId());

        return "redirect:/home";
    }
}
