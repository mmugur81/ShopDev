package com.mmugur81.service;

import com.mmugur81.model.User;
import com.mmugur81.model.UserRole;

import java.util.List;

/**
 * Created by mugurel on 01.11.2016.
 */
public interface UserService {

    User registerUser(String firstName, String lastName, String email, String password);

    User registerByModel(User newUser);

    User getAuthenticatedUser();

    void addRole(User user, UserRole.Role role);

    User get(long id);

    List<User> getAll();

    User disableUser(User user);
}
