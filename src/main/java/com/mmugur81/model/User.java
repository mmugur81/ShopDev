package com.mmugur81.model;

import com.mmugur81.validator.EmailUnique;
import com.mmugur81.validator.PasswordMatches;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@PasswordMatches
public class User extends BaseModel {

    // Max 20 chars
    public static enum Status {
        Pending,
        Active,
        Disabled
    }

    /******************************************************************************************************************/

    @Size(min=3, max=30)
    @Column(length = 30)
    private String firstName;

    @Size(min=3, max=30)
    @Column(length = 30)
    private String lastName;

    @NotEmpty
    @Email
    @EmailUnique
    private String email;

    @Size(min=5, max=30)
    @Column(length = 64)
    private String password;

    @Transient
    private String passwordConfirm;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, columnDefinition = "CHAR(20)")
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void addRole(UserRole.Role role) {
        // Prevent duplicates
        if (!this.hasRole(role)) {
            this.userRoles.add(new UserRole(this, role));
        }
    }

    public boolean hasRole(UserRole.Role role) {
        for (UserRole ur : this.userRoles) {
            if (ur.getRole().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public boolean isActive() {
        return this.status == Status.Active;
    }
}
