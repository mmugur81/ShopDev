package com.mmugur81.validator;

import org.springframework.transaction.annotation.Transactional;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mugurel on 24.08.2016.
 * User exists validation annotation
 */

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserExistsValidator.class)
@Transactional
public @interface UserExists {
    String message() default "Specified user doesn't exist!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
