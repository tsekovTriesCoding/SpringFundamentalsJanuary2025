package bg.softuni.springfundamentalsretakeexam15december2021.vallidation;

import bg.softuni.springfundamentalsretakeexam15december2021.service.ProductService;
import bg.softuni.springfundamentalsretakeexam15december2021.vallidation.annotation.UniqueProductName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {
    private final ProductService productService;

    public UniqueProductNameValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.productService.getProductByName(value) == null;
    }
}
