package bg.softuni.battleships.model.dto;

import bg.softuni.battleships.model.enums.CategoryEnum;
import bg.softuni.battleships.vallidation.annotation.UniqueShipName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddShipDTO {
    @NotNull
    @Size(min = 3, max = 20, message = "Name length must be between 2 and 10 characters.")
    @UniqueShipName
    private String name;

    @NotNull
    @Positive(message = "The health must be positive.")
    private Long health;

    @NotNull
    @Positive(message = "The power must be positive.")
    private Long power;

    @NotNull
    @PastOrPresent(message = "Created date cannot be in the future.")
    private LocalDate created;

    @NotNull(message = "You must select a category.")
    private CategoryEnum category;

    public AddShipDTO() {
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public AddShipDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public AddShipDTO setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public Long getHealth() {
        return health;
    }

    public AddShipDTO setHealth(Long health) {
        this.health = health;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getPower() {
        return power;
    }

    public AddShipDTO setPower(Long power) {
        this.power = power;
        return this;
    }

}
