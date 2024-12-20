package com.plannerapp.controller;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.service.impl.TaskServiceImpl;
import com.plannerapp.service.impl.UserServiceImpl;
import com.plannerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController {
    private final UserServiceImpl userService;
    private final TaskServiceImpl taskService;
    private final LoggedUser loggedUser;

    @Autowired
    public HomeControllerImpl(UserServiceImpl userService, TaskServiceImpl taskService, LoggedUser loggedUser) {
        this.userService = userService;
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }

    @Override
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @Override
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = userService.findUserById(loggedUser.getId()).orElse(null);
        model.addAttribute("currentUserInfo", user);

        List<Task> usersAssignedTasks = this.userService.getAssignedTasksFromCurrentUser(this.loggedUser.getId());
        model.addAttribute("usersAssignedTasks", usersAssignedTasks);

/*
 List<UsersWithTasksDTO> tasksFromOtherUsers = this.userService.getTasksFromOtherUsers(this.loggedUser.getId());
 model.addAttribute("otherUserTasks", tasksFromOtherUsers);
*/

        List<Task> allUnassignedTasks = this.taskService.getAllUnassignedTasks();
        model.addAttribute("allUnassignedTasks", allUnassignedTasks);
        model.addAttribute("totalUnassignedTasks", allUnassignedTasks.size());

        return "home";
    }
}
