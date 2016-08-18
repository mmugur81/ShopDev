package com.mmugur81.service;

import com.mmugur81.model.User;
import com.mmugur81.model.UserRole;
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

    public User registerUser(String firstName, String lastName, String email, String password) {
        User newUser = new User(firstName, lastName, email, password);
        newUser = userRepo.saveAndFlush(newUser);
        return newUser;
    }

    public void addRole(User user, UserRole.Role role) {
        user.addRole(role);
        userRepo.saveAndFlush(user);
    }

    public User get(long id) {
        return userRepo.findOne(id);
    }
}
