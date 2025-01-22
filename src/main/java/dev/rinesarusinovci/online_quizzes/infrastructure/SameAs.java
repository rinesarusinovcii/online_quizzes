package dev.rinesarusinovci.online_quizzes.infrastructure;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SameAsValidator.class)
@Documented
public @interface SameAs {
    String message() default "You should be at least 18 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();
}
