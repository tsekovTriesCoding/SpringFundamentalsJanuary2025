package bg.softuni.coffeeshop.model.dto;

import bg.softuni.coffeeshop.model.enums.CategoryEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddOrderDTO {
    @NotNull
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @NotNull
    @Positive(message = "The price must be positive")
    private BigDecimal price;

    @NotNull
    @PastOrPresent(message = "Order time cannot be in the future")
    private LocalDateTime orderTime;

    @NotNull(message = "You must select a category")
    private CategoryEnum category;

    @NotNull
    @Size(min = 5, message = "Description size must be minimum 5 char")
    private String description;

    public AddOrderDTO() {
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public AddOrderDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOrderDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddOrderDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public AddOrderDTO setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOrderDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
