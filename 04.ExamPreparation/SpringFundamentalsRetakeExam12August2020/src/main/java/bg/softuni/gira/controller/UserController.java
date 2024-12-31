package bg.softuni.gira.controller;

import bg.softuni.gira.model.dto.LoginDTO;
import bg.softuni.gira.service.UserService;
import bg.softuni.gira.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private final LoggedUser loggedUser;
    private final UserService userService;

    public UserController(LoggedUser loggedUser,
                          UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @ModelAttribute
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }

    @GetMapping("/login")
    String login() {
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    String loginConfirm(@Valid LoginDTO loginDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", result);

            return "redirect:/users/login";
        }

        boolean validCredentials = this.userService.checkCredentials(loginDTO.getEmail(), loginDTO.getPassword());

        if (!validCredentials) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("validCredentials", false);
            return "redirect:/users/login";
        }

        this.userService.login(loginDTO.getEmail());

        return "redirect:/home";
    }
}
