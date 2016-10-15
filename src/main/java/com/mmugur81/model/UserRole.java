package com.mmugur81.model;

import javax.persistence.*;

/**
 * Created by mugurel on 18.08.2016.
 * User role model
 */

@Entity
@Table(
    name = "user_roles",
    uniqueConstraints = {@UniqueConstraint(
            name = "uq_user_roles", columnNames = {"id_user", "role"}
    )}
)
public class UserRole extends BaseModel {

    public static enum Role {
        ADMIN,
        USER,
        ORDER_ADMIN
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_user_roles_user_id"))
    private User user;

    @Enumerated(EnumType.STRING)
    private Role role;

    /******************************************************************************************************************/

    public UserRole() { }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    /******************************************************************************************************************/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
