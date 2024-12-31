package bg.softuni.gira.vallidation.annotation;

import bg.softuni.gira.vallidation.UniqueTaskValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueTaskValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueTask {
    String message() default "Task name already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
