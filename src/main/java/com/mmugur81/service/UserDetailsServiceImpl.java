package com.mmugur81.service;

import com.mmugur81.model.User;
import com.mmugur81.model.UserRole;
import com.mmugur81.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mugurel on 18.08.2016.
 * User details security
 */

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;

    public UserDetailsServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user with email " + username);
        }

        org.springframework.security.core.userdetails.User ud = buildUserForAuthentication(user);
        return ud;
    }

    private org.springframework.security.core.userdetails.User
    buildUserForAuthentication(User user) {

        // Build user's authorities
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole ur : user.getUserRoles()) {
            authorities.add(new SimpleGrantedAuthority(ur.getRole().toString()));
        }

        // Build and return the user
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(),
            user.isActive(), true, true, true, authorities
        );
    }

}
