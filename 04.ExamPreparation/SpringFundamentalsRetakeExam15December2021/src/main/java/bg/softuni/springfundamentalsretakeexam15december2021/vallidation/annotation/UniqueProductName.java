package bg.softuni.springfundamentalsretakeexam15december2021.vallidation.annotation;

import bg.softuni.springfundamentalsretakeexam15december2021.vallidation.UniqueProductNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueProductNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueProductName {
    String message() default "Product already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
