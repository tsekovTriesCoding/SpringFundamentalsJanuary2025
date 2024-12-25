package bg.softuni.springfundamentalsretakeexam15december2021.controller;


import bg.softuni.springfundamentalsretakeexam15december2021.model.dto.LoginDTO;
import bg.softuni.springfundamentalsretakeexam15december2021.model.dto.RegisterDTO;
import bg.softuni.springfundamentalsretakeexam15december2021.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private final LoggedUser loggedUser;

    public UserController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @GetMapping("/login")
    String login(Model model) {
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    String loginConfirm(@Valid LoginDTO loginDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        
        return "redirect:/home";
    }

    @GetMapping("/register")
    String register() {
        return "register";
    }

    @PostMapping("/register")
    String registerConfirm(@Valid RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        return "registerConfirm";
    }

    @GetMapping("/logout")
    String logout() {
        return "logout";
    }
}
