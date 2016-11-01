package com.mmugur81.service;

import com.mmugur81.model.User;
import com.mmugur81.model.UserRole;
import com.mmugur81.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mugurel on 13.08.2016.
 * User service
 */

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /******************************************************************************************************************/

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) {
        User newUser = new User(firstName, lastName, email, password);

        return this.registerByModel(newUser);
    }

    @Override
    public User registerByModel(User newUser) {
        // Encode password
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.addRole(UserRole.Role.USER);
        newUser.setStatus(User.Status.Active);

        // Save user to DB
        User savedUser = userRepo.saveAndFlush(newUser);
        if (savedUser == null) {
            return null;
        }

        // Authenticate newly registered user
        Authentication auth = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return savedUser;
    }

    @Override
    public User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return userRepo.findByEmail(((UserDetails) principal).getUsername());
        }
        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    @Override
    public void addRole(User user, UserRole.Role role) {
        user.addRole(role);
        userRepo.saveAndFlush(user);
    }

    @Override
    public User get(long id) {
        return userRepo.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User disableUser(User user) {
        user.setStatus(User.Status.Disabled);

        // "Disable" email to allow registering the same email for a new account
        user.setEmail(user.getEmail() + "_dsbld_" + Long.toString(user.getId()));

        return userRepo.saveAndFlush(user);
    }
}
