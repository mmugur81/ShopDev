package com.mmugur81.validator;

import com.mmugur81.model.User;
import com.mmugur81.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Mugurel on 25.09.2016.
 * User exists validator
 */

public class UserExistsValidator implements ConstraintValidator<UserExists, Object> {

    private UserRepository userRepo;

    @Autowired
    public UserExistsValidator(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void initialize(UserExists userExists) { }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepo.findOne((Long) o);

        return user != null;
    }
}
