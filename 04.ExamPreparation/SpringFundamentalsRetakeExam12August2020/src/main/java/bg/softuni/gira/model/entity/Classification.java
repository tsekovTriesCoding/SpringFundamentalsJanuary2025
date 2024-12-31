package bg.softuni.gira.model.entity;

import bg.softuni.gira.model.enums.ClassificationEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "classifications")
public class Classification extends BaseEntity {
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private ClassificationEnum name;

    @Column
    private String description;

    public Classification() {
    }

    public Classification(ClassificationEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Classification setDescription(String description) {
        this.description = description;
        return this;
    }

    public ClassificationEnum getName() {
        return name;
    }

    public Classification setName(ClassificationEnum name) {
        this.name = name;
        return this;
    }
}
