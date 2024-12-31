package bg.softuni.gira.model.entity;

import bg.softuni.gira.model.enums.Progress;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Progress progress;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @ManyToOne(optional = false)
    private Classification classification;

    @ManyToOne(optional = false)
    private User user;

    public Task() {
    }

    public Classification getClassification() {
        return classification;
    }

    public Task setClassification(Classification classification) {
        this.classification = classification;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Task setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public Progress getProgress() {
        return progress;
    }

    public Task setProgress(Progress progress) {
        this.progress = progress;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Task setUser(User user) {
        this.user = user;
        return this;
    }
}
