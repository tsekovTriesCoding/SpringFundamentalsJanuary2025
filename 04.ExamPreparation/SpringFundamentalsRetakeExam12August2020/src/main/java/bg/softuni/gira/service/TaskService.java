package bg.softuni.gira.service;

import bg.softuni.gira.model.dto.TaskInfoDTO;

import java.util.Set;

public interface TaskService {
    Set<TaskInfoDTO> getAll();

    void init();

    void progress(Long taskId);
}
