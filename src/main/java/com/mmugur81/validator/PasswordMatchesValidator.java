package com.mmugur81.validator;

import com.mmugur81.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by mugurel on 24.08.2016.
 * Password Matches the confirmPassword
 */

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) { }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        User user = (User) obj;
        return user.getPassword().equals(user.getPasswordConfirm());
    }
}
