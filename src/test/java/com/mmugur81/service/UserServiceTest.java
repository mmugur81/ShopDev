package com.mmugur81.service;

import com.mmugur81.model.User;
import com.mmugur81.model.UserRole;
import org.junit.After;
import org.junit.Assert;
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
        // Given - user already created in before()

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

    @Test
    public void getSavedUserTest() {
        // Given - user already created in before()
        user.setStatus(User.Status.Pending);
        User savedUser = userService.registerByModel(user);

        // When
        User loadedUser = userService.get(savedUser.getId());

        // Then
        Assert.assertEquals(savedUser.getFirstName(), loadedUser.getFirstName());
        Assert.assertEquals(savedUser.getLastName(), loadedUser.getLastName());
        Assert.assertEquals(savedUser.getEmail(), loadedUser.getEmail());
        Assert.assertEquals(savedUser.getPassword(), loadedUser.getPassword());
        Assert.assertEquals(savedUser.getStatus(), loadedUser.getStatus());
    }

    @Test
    public void addUserRoleTest() {
        // Given
        user.addRole(UserRole.Role.USER);
        user.addRole(UserRole.Role.ADMIN);

        // When
        User savedUser = userService.registerByModel(user);

        // Then
        assertThat(savedUser.getUserRoles(), hasSize(2));
        Assert.assertTrue(savedUser.hasRole(UserRole.Role.USER));
        Assert.assertTrue(savedUser.hasRole(UserRole.Role.ADMIN));
    }

    @Test
    public void userHasDefaultRoleTest() {
        // Given - user already created in before()

        // When
        User savedUser = userService.registerByModel(user);

        // Then
        Assert.assertTrue(savedUser.hasRole(UserRole.Role.USER));
    }

    @Test
    public void userDoesntHaveRoleTest() {
        // Given
        user.addRole(UserRole.Role.USER);

        // When
        User savedUser = userService.registerByModel(user);

        // Then
        Assert.assertFalse(savedUser.hasRole(UserRole.Role.ADMIN));
    }

    @Test
    public void disableUserTest() {
        // Given - user already created in before()

        // When
        User disabledUser = userService.disable(user);

        // Then
        assertThat(disabledUser, hasProperty("status", equalTo(User.Status.Disabled)));
        assertThat(disabledUser, hasProperty("email", not(equalTo(email))));
    }
}
