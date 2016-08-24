package com.mmugur81.validator;

import com.mmugur81.model.User;
import com.mmugur81.repository.UserRepository;
import com.mmugur81.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by mugurel on 24.08.2016.
 * Email unique validator
 */


public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, Object> {

    private UserRepository userRepo;

    @Autowired
    public EmailUniqueValidator(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void initialize(EmailUnique emailUnique) { }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepo.findByEmail((String) o);

        return user == null;
    }
}
