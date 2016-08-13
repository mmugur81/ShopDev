package com.mmugur81.repository;

import com.mmugur81.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mugurel on 13.08.2016.
 * User repository
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
