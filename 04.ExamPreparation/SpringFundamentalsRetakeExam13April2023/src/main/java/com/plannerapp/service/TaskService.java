package com.plannerapp.service;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.model.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllUnassignedTasks();

    void addTask(AddTaskDTO addTaskDTO);

    void assignTask(Long taskId, Long userId);

    void removeTask(Long taskId, Long userId);

    void returnTask(Long taskId, Long userId);
}
