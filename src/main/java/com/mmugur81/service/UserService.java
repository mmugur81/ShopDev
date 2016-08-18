package com.mmugur81.service;

import com.mmugur81.model.User;
import com.mmugur81.model.UserRole;
import com.mmugur81.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Mugurel on 13.08.2016.
 * User service
 */

@Service
public class UserService {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /******************************************************************************************************************/

    public User registerUser(String firstName, String lastName, String email, String password) {
        User newUser = new User(firstName, lastName, email, password);
        newUser.setPassword(passwordEncoder.encode(password));

        User savedUser = userRepo.saveAndFlush(newUser);
        if (savedUser == null) {
            return null;
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(
            newUser, null, new ArrayList<>());

        SecurityContextHolder.getContext().setAuthentication(auth);

        return savedUser;
    }

    public void addRole(User user, UserRole.Role role) {
        user.addRole(role);
        userRepo.saveAndFlush(user);
    }

    public User get(long id) {
        return userRepo.findOne(id);
    }
}
