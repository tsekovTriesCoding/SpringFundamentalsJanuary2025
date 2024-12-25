package bg.softuni.springfundamentalsretakeexam15december2021.model.entity;

import bg.softuni.springfundamentalsretakeexam15december2021.model.enums.CategoryEnum;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private CategoryEnum name;

    @Column
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category() {
        this.products = new HashSet<>();
    }

    public Category(CategoryEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryEnum getName() {
        return name;
    }

    public Category setName(CategoryEnum name) {
        this.name = name;
        return this;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Category setProducts(Set<Product> products) {
        this.products = products;
        return this;
    }
}
