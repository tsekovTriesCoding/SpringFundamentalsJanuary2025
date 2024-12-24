package bg.softuni.linkedout.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "companies"
)
public class Company {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private String id;
    @Column(
            nullable = false
    )
    private Double budget;
    @Column(
            columnDefinition = "TEXT",
            nullable = false
    )
    private String description;
    @Column(
            nullable = false,
            unique = true
    )
    private String name;
    @Column(
            nullable = false
    )
    private String town;

    public Company() {
    }

    public String getId() {
        return this.id;
    }

    public Company setId(String id) {
        this.id = id;
        return this;
    }

    public Double getBudget() {
        return this.budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}