package bg.softuni.gira.service;

import bg.softuni.gira.model.dto.AddTaskDTO;
import bg.softuni.gira.model.dto.TaskInfoDTO;
import bg.softuni.gira.model.entity.Task;

import java.util.Set;

public interface TaskService {
    Set<TaskInfoDTO> getAll();

    void init();

    void progress(Long taskId);

    void add(AddTaskDTO addTaskDTO, Long userId);

    Task getByName(String value);
}
