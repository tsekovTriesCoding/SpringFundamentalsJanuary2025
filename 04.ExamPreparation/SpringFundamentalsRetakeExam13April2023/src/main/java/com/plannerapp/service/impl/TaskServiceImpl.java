package com.plannerapp.service.impl;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final PriorityServiceImpl priorityService;
    private final UserServiceImpl userService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           UserRepository userRepository,
                           PriorityServiceImpl priorityService,
                           UserServiceImpl userService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.priorityService = priorityService;
        this.userService = userService;
    }

    @Override
    public List<Task> getAllUnassignedTasks() {
        return this.taskRepository.getAllByUserIsNull();
    }

    @Override
    public void addTask(AddTaskDTO addTaskDTO) {
        Task task = new Task();
        Priority priority = this.priorityService.findPriority(addTaskDTO.getPriority());

        task.setPriority(priority);
        task.setDescription(addTaskDTO.getDescription());
        task.setDueDate(addTaskDTO.getDueDate());

        this.taskRepository.save(task);
    }

    @Override
    public void assignTask(Long taskId, Long userId) {
        User currentUser = this.userService.findUserById(userId).orElse(null);
        Task taskById = this.taskRepository.findById(taskId).orElse(null);

        taskById.setUser(currentUser);
        this.taskRepository.save(taskById);

        currentUser.getAssignedTasks().add(taskById);

        this.userRepository.save(currentUser);
    }

    @Override
    public void removeTask(Long taskId, Long userId) {
        User currentUser = this.userService.findUserById(userId).orElse(null);
        Task taskById = this.taskRepository.findById(taskId).orElse(null);

        currentUser.getAssignedTasks().remove(taskById);
        this.userRepository.save(currentUser);
        this.taskRepository.delete(taskById);
    }

    @Override
    public void returnTask(Long taskId, Long userId) {
        User currentUser = this.userService.findUserById(userId).orElse(null);
        Task taskById = this.taskRepository.findById(taskId).orElse(null);

        Task taskToReturn = currentUser.getAssignedTasks().stream().filter(e -> e.getId() == taskId).findFirst().orElse(null);
        currentUser.getAssignedTasks().remove(taskToReturn);

        taskById.setUser(null);

        this.taskRepository.save(taskById);
        this.userRepository.save(currentUser);
    }
}
