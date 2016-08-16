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

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Type type;

    /******************************************************************************************************************/

    public User() { }

    public User(String name, String email, Type type) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.status = Status.Pending;
    }

    /******************************************************************************************************************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
