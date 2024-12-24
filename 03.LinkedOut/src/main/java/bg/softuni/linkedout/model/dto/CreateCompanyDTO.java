package bg.softuni.linkedout.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateCompanyDTO {
    private @NotNull(
            message = "{add.company.budget.not.null}"
    ) @Positive(
            message = "{add.company.budget.not.positive}"
    ) Double budget;
    private @NotNull(
            message = "{add.company.description.not.null}"
    ) @Size(
            min = 10,
            message = "{add.company.description.min.size}"
    ) String description;
    private @NotNull(
            message = "{add.company.name.not.null}"
    ) @Size(
            min = 2,
            max = 10,
            message = "{add.company.name.min.size}"
    ) String name;
    private @NotNull(
            message = "{add.company.town.not.null}"
    ) @Size(
            min = 2,
            max = 10,
            message = "{add.company.town.min.size}"
    ) String town;

    public CreateCompanyDTO() {
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