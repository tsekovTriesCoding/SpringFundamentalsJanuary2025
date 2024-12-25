package bg.softuni.springfundamentalsretakeexam15december2021.model.dto;

import java.math.BigDecimal;

public class ProductInfoDTO {
    private String name;
    private BigDecimal price;

    public ProductInfoDTO() {
    }

    public String getName() {
        return name;
    }

    public ProductInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductInfoDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
