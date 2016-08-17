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

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /******************************************************************************************************************/

    public User registerUser(String firstName, String lastName, String email, String password, User.Type type) {
        User newUser = new User(firstName, lastName, email, password, type);
        newUser = userRepo.saveAndFlush(newUser);
        return newUser;
    }

    public User get(long id) {
        return userRepo.findOne(id);
    }
}
