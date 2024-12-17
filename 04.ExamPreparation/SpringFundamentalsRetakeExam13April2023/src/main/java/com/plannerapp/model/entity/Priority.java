package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity {
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private PriorityEnum name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    private List<Task> tasks;

    public Priority() {
        this.tasks = new ArrayList<>();
    }

    public Priority(PriorityEnum name, String description) {
        this.name = name;
        this.description = description;
    }

    public PriorityEnum getName() {
        return name;
    }

    public Priority setName(PriorityEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Priority setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Priority setTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }
}
