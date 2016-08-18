package com.mmugur81.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mugurel on 10.08.2016.
 * User Model
 */

@Entity
@Table(
    name = "users",
    uniqueConstraints = {@UniqueConstraint(name = "uq_users_email", columnNames = "email")}
)
public class User extends BaseModel {

    public static enum Status {
        Pending,
        Active,
        Disabled
    }

    /******************************************************************************************************************/

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>();

    /******************************************************************************************************************/

    public User() { }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = Status.Pending;
        this.addRole(UserRole.Role.USER);
    }

    /******************************************************************************************************************/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void addRole(UserRole.Role role) {
        this.userRoles.add(new UserRole(this, role));
    }

    public boolean hasRole(UserRole.Role role) {
        for (UserRole ur : this.userRoles) {
            if (ur.getRole().equals(role)) {
                return true;
            }
        }
        return false;
    }
}
