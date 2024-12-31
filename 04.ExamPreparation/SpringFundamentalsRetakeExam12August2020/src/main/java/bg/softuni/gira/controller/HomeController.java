package bg.softuni.gira.controller;

import bg.softuni.gira.model.dto.TaskInfoDTO;
import bg.softuni.gira.service.TaskService;
import bg.softuni.gira.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final TaskService taskService;

    public HomeController(LoggedUser loggedUser,
                          TaskService taskService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
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

        Set<TaskInfoDTO> allTasks = this.taskService.getAll();
        model.addAttribute("allTasks", allTasks);

        return "home";
    }
}
