package com.company.restaulator.areas.user.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = PasswordMatchingValidator.class)
public @interface ValidPassword {

    String message();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}