package bg.softuni.gira.controller;

import bg.softuni.gira.model.dto.LoginDTO;
import bg.softuni.gira.model.dto.RegisterDTO;
import bg.softuni.gira.service.UserService;
import bg.softuni.gira.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    @ModelAttribute
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
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

    @GetMapping("/register")
    String register() {
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    String registerConfirm(@Valid RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmPassword",
                            "Passwords don't match."));
        }

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", result);

            return "redirect:/users/register";
        }

        this.userService.register(registerDTO);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    String logout() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.userService.logout();

        return "redirect:/";
    }
}
