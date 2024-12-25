package bg.softuni.springfundamentalsretakeexam15december2021.model.dto;

import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.Category;
import bg.softuni.springfundamentalsretakeexam15december2021.model.enums.CategoryEnum;
import bg.softuni.springfundamentalsretakeexam15december2021.vallidation.annotation.UniqueProductName;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddProductDTO {
    @NotNull
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    @UniqueProductName
    private String name;

    @Size(min = 5, message = "Description length must be more than 5 characters!")
    private String description;

    @FutureOrPresent(message = "The date cannot be in the past!")
    private LocalDateTime neededBefore;

    @Positive(message = "The price must be a positive number!")
    private BigDecimal price;

    @NotNull(message = "Category cannot be null. ")
    private CategoryEnum category;

    public AddProductDTO() {
    }

    public String getDescription() {
        return description;
    }

    public AddProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public AddProductDTO setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddProductDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public AddProductDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
