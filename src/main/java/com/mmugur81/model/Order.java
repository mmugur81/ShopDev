package com.mmugur81.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by mugurel on 13.09.2016.
 * Order model
 */

@Entity
@Table(name = "orders")
public class Order extends BaseModel {

    // Max 20 chars
    public static enum Status {
        Pending,
        Confirmed,
        Cancelled
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_orders_users_id"))
    private User user;

    @NotNull
    @Embedded
    private Price total;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, columnDefinition = "CHAR(20)")
    private Status status;

    private String notes;

    private boolean payed;

    private Date payDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    /******************************************************************************************************************/

    public Order() {
        this.status = Status.Pending;
        this.payed = false;
    }

    /******************************************************************************************************************/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Price getTotal() {
        return total;
    }

    public void setTotal(Price total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}
