package bg.softuni.gira.model.dto;

import java.time.LocalDate;

public class TaskInfoDTO {
    private Long id;
    private String name;
    private String assignedTo;
    private String classification;
    private LocalDate dueDate;
    private String progress;

    public TaskInfoDTO() {
    }

    public Long getId() {
        return id;
    }

    public TaskInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public TaskInfoDTO setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public String getClassification() {
        return classification;
    }

    public TaskInfoDTO setClassification(String classification) {
        this.classification = classification;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskInfoDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getName() {
        return name;
    }

    public TaskInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getProgress() {
        return progress;
    }

    public TaskInfoDTO setProgress(String progress) {
        this.progress = progress;
        return this;
    }
}
