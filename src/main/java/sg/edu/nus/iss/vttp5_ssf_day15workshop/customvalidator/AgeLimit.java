package sg.edu.nus.iss.vttp5_ssf_day15workshop.customvalidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD) // Specifies where your annotation can be applied
@Retention(RetentionPolicy.RUNTIME) // Specifies when the custom validation information should be available
@Constraint(validatedBy = AgeLimitValidator.class) // Specifies the validator class that will implement the custom validation logic
public @interface AgeLimit {
    int minimumAge() default 10;
    int maximumAge() default 100;
    String message() default "User must be between 18 and 100 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
