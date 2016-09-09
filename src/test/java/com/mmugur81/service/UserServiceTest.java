package com.mmugur81.service;

import com.mmugur81.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by mugurel on 09.09.2016.
 * UserService test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ComponentScan("com.mmugur81")
public class UserServiceTest {

    // Sample values for a new user
    private static final String firstName = "Test";
    private static final String lastName = "User";
    private static final String email = "test@test.test";
    private static final String password = "randpass";

    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void before() {
        user = new User(firstName, lastName, email, password);
    }

    @After
    public void after() {
        if (user != null && user.getId() != 0) {
            userService.disable(user);
        }
    }

    @Test
    public void registerUserTest() {
        // Given - user already created in before

        // When
        User savedUser = userService.registerByModel(user);

        // Then
        assertThat(savedUser, hasProperty("id", greaterThan(0L)));
        assertThat(savedUser, hasProperty("firstName", equalTo(firstName)));
        assertThat(savedUser, hasProperty("lastName", equalTo(lastName)));
        assertThat(savedUser, hasProperty("email", equalTo(email)));

        // and that password has been hashed
        assertThat(savedUser, hasProperty("password", not(equalTo(password))));
    }
}
