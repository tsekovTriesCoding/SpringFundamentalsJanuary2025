package bg.softuni.gira.model.dto;

import bg.softuni.gira.model.enums.ClassificationEnum;
import bg.softuni.gira.vallidation.annotation.UniqueTask;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddTaskDTO {
    @UniqueTask
    @NotNull
    @Size(min = 3, max = 20, message = "Name length must  be between 3 and 20 characters!")
    private String name;

    @NotNull
    @Size(min = 5, message = "Description length must be more than 5 characters!")
    private String description;

    @NotNull
    @FutureOrPresent(message = "The date cannot be in the past!")
    private LocalDate dueDate;

    @NotNull(message = "Classification cannot be null!")
    private ClassificationEnum classification;

    public AddTaskDTO() {
    }

    public ClassificationEnum getClassification() {
        return classification;
    }

    public AddTaskDTO setClassification(ClassificationEnum classification) {
        this.classification = classification;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddTaskDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public AddTaskDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddTaskDTO setName(String name) {
        this.name = name;
        return this;
    }
}
