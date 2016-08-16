package com.mmugur81.service;

import com.mmugur81.model.User;
import com.mmugur81.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mugurel on 13.08.2016.
 * User service
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User registerUser(String name, String email, User.Type type) {
        User newUser = new User(name, email, type);
        newUser = userRepo.saveAndFlush(newUser);
        return newUser;
    }

    public User get(long id) {
        return userRepo.findOne(id);
    }
}
