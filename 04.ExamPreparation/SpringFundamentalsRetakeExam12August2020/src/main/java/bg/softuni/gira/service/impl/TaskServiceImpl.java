package bg.softuni.gira.service.impl;

import bg.softuni.gira.model.dto.TaskInfoDTO;
import bg.softuni.gira.model.entity.Classification;
import bg.softuni.gira.model.entity.Task;
import bg.softuni.gira.model.entity.User;
import bg.softuni.gira.model.enums.ClassificationEnum;
import bg.softuni.gira.model.enums.Progress;
import bg.softuni.gira.repository.ClassificationRepository;
import bg.softuni.gira.repository.TaskRepository;
import bg.softuni.gira.repository.UserRepository;
import bg.softuni.gira.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ClassificationRepository classificationRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           ClassificationRepository classificationRepository,
                           UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.classificationRepository = classificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Set<TaskInfoDTO> getAll() {
        return this.taskRepository.findAll()
                .stream()
                .map(t -> {
                    TaskInfoDTO dto = new TaskInfoDTO();
                    dto.setId(t.getId());
                    dto.setName(t.getName());
                    dto.setClassification(t.getClassification().getName().toString());
                    dto.setAssignedTo(t.getUser().getUsername());
                    dto.setProgress(t.getProgress().toString());
                    dto.setDueDate(t.getDueDate());
                    return dto;
                }).collect(Collectors.toSet());
    }

    @Override
    public void init() {
        if (this.taskRepository.count() > 0) {
            return;
        }

        User user = this.userRepository.findById(1L).orElse(null);
        Classification support = this.classificationRepository.findByName(ClassificationEnum.SUPPORT)
                .orElse(null);
        Classification feature = this.classificationRepository.findByName(ClassificationEnum.FEATURE)
                .orElse(null);
        Classification bug = this.classificationRepository.findByName(ClassificationEnum.BUG)
                .orElse(null);
        Classification other = this.classificationRepository.findByName(ClassificationEnum.OTHER)
                .orElse(null);

        Task task1 = new Task();
        task1.setName("Dark mode");
        task1.setDescription("Task 1");
        task1.setClassification(feature);
        task1.setProgress(Progress.OPEN);
        task1.setDueDate(LocalDate.now());
        task1.setUser(user);
        task1.setDescription("Task 1 description");

        this.taskRepository.save(task1);

        Task task2 = new Task();
        task2.setName("New laptop");
        task2.setClassification(support);
        task2.setProgress(Progress.OPEN);
        task2.setDueDate(LocalDate.now());
        task2.setUser(user);
        task2.setDescription("Task 2 description");
        this.taskRepository.save(task2);

        Task task3 = new Task();
        task3.setName("Login breaks");
        task3.setClassification(bug);
        task3.setProgress(Progress.OPEN);
        task3.setDueDate(LocalDate.now());
        task3.setUser(user);
        task3.setDescription("Task 3 description");
        this.taskRepository.save(task3);

        Task task4 = new Task();
        task4.setName("Restock apples");
        task4.setClassification(other);
        task4.setProgress(Progress.OPEN);
        task4.setDueDate(LocalDate.now());
        task4.setUser(user);
        task4.setDescription("Task 4 description");
        this.taskRepository.save(task4);
    }

    @Override
    public void progress(Long taskId) {
        Task task = this.taskRepository.findById(taskId).orElse(null);

        if (task.getProgress() == Progress.OPEN) {
            task.setProgress(Progress.IN_PROGRESS);
            this.taskRepository.save(task);
        } else if (task.getProgress() == Progress.IN_PROGRESS) {
            task.setProgress(Progress.COMPLETED);
            this.taskRepository.save(task);
        } else if (task.getProgress() == Progress.COMPLETED) {
            this.taskRepository.delete(task);
        }
    }
}
