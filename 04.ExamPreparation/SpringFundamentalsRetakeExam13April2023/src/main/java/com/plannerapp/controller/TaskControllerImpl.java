package com.plannerapp.controller;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.service.impl.TaskServiceImpl;
import com.plannerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskControllerImpl implements TaskController {
    private final TaskServiceImpl taskService;
    private final LoggedUser loggedUser;

    @Autowired
    public TaskControllerImpl(TaskServiceImpl taskService, LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute
    public AddTaskDTO addTaskDTO() {
        return new AddTaskDTO();
    }

    @Override
    public String addTask() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "task-add";
    }

    @Override
    public String addTask(AddTaskDTO addTaskDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addTaskDTO", addTaskDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addTaskDTO", result);

            return "redirect:/tasks/add-task";
        }

        this.taskService.addTask(addTaskDTO);
        return "redirect:/home";
    }

    @Override
    public String assignTask(Long id) {
        this.taskService.assignTask(id, loggedUser.getId());

        return "redirect:/home";
    }

    @Override
    public String removeTask(Long id) {
        taskService.removeTask(id,loggedUser.getId());

        return "redirect:/home";
    }

    @Override
    public String returnTask(Long id) {
        taskService.returnTask(id, loggedUser.getId());

        return "redirect:/home";
    }
}
