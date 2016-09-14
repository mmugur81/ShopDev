package com.mmugur81.REST_model;

import com.mmugur81.model.Order;
import com.mmugur81.model.Price;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mugurel on 14.09.2016.
 * REST Order model
 */
public class RestOrder {

    private long id;

    private long userId;

    private Price total;

    private Order.Status status;

    private String notes;

    private boolean payed;

    private Date payDate;

    private List<RestOrderItem> orderItems;

    /******************************************************************************************************************/

    public synchronized void addOrderItem(short itemNumber, RestProduct product, Price price) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(new RestOrderItem(itemNumber, product, price));
    }

    /******************************************************************************************************************/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Price getTotal() {
        return total;
    }

    public void setTotal(Price total) {
        this.total = total;
    }

    public Order.Status getStatus() {
        return status;
    }

    public void setStatus(Order.Status status) {
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

    public List<RestOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<RestOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
