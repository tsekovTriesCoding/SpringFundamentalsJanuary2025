package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ConditionEnum name;

    @Column(nullable = false)
    private String description;

    public Condition() {
    }

    public ConditionEnum getName() {
        return name;
    }

    public Condition setName(ConditionEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Condition setDescription(String description) {
        this.description = description;
        return this;
    }
}
