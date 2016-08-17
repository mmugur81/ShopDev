package com.mmugur81.model;

import javax.persistence.*;

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

    public static enum Type {
        Admin,
        Manager,
        Client
    }

    /******************************************************************************************************************/

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Type type;

    /******************************************************************************************************************/

    public User() { }

    public User(String firstName, String lastName, String email, String password, Type type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.status = (type == Type.Admin)? Status.Active : Status.Pending;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
