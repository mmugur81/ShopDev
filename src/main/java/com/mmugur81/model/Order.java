package com.mmugur81.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    /******************************************************************************************************************/

    public Order() {
        this.status = Status.Pending;
        this.payed = false;
        this.orderItems = new ArrayList<>();
    }

    public Order(User user, Currency currency) {
        this();
        this.user = user;
        this.setTotal(new Price(0L, currency));
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public synchronized short addProductItem(Product product) {
        short itemNumber = (short) (this.orderItems.size() + 1);

        OrderItem orderItem = new OrderItem(this, itemNumber, product);

        this.orderItems.add(orderItem);

        this.total.add(product.getPrice());

        return itemNumber;
    }

    public synchronized boolean removeProductItem(short itemNumber) {
        OrderItem toBeRemoved = null;
        for (OrderItem item : this.orderItems) {
            if (item.getItemNumber() == itemNumber) {
                toBeRemoved = item;
                break;
            }
        }
        if (toBeRemoved != null) {
            this.orderItems.remove(toBeRemoved);
            this.total.subtract(toBeRemoved.getPrice());
            return true;
        } else {
            return false;
        }
    }

    public void registerPayment() {
        this.setPayed(true);
        this.setPayDate(new Date());
    }
}
