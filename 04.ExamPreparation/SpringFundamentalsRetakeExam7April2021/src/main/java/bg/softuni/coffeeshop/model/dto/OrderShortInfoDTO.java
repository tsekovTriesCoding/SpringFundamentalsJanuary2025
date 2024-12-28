package bg.softuni.coffeeshop.model.dto;

import bg.softuni.coffeeshop.model.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderShortInfoDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;
    private LocalDateTime orderTime;

    public OrderShortInfoDTO() {
    }

    public Long getId() {
        return id;
    }

    public OrderShortInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderShortInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderShortInfoDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public OrderShortInfoDTO setCategory(Category category) {
        this.category = category;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderShortInfoDTO setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }
}
