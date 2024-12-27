package bg.softuni.battleships.vallidation.annotation;

import bg.softuni.battleships.vallidation.UniqueShipNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueShipNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueShipName {
    String message() default "Ship already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
