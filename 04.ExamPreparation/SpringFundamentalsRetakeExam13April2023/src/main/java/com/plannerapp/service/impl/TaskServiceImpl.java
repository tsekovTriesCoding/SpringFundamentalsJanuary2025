package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Task;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllUnassignedTasks() {
        return this.taskRepository.getAllByUserIsNull();
    }
}
