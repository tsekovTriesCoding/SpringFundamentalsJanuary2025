package bg.softuni.gira.controller;

import bg.softuni.gira.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/progress/{id}")
    public String progress(@PathVariable Long id) {
        this.taskService.progress(id);

        return "redirect:/home";
    }
}
