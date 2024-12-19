package com.plannerapp.service;

import com.plannerapp.model.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllUnassignedTasks();
}
