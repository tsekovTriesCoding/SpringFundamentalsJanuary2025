package bg.softuni.gira.controller;

import bg.softuni.gira.model.dto.AddTaskDTO;
import bg.softuni.gira.service.TaskService;
import bg.softuni.gira.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final LoggedUser loggedUser;

    public TaskController(TaskService taskService,
                          LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute
    public AddTaskDTO addTaskDTO() {
        return new AddTaskDTO();
    }

    @GetMapping("/progress/{id}")
    public String progress(@PathVariable Long id) {
        this.taskService.progress(id);

        return "redirect:/home";
    }

    @GetMapping("/add")
    public String add() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "add-task";
    }

    @PostMapping("/add")
    public String add(@Valid AddTaskDTO addTaskDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addTaskDTO", addTaskDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addTaskDTO", result);

            return "redirect:/tasks/add";
        }

        this.taskService.add(addTaskDTO, this.loggedUser.getId());

        return "redirect:/home";
    }
}
