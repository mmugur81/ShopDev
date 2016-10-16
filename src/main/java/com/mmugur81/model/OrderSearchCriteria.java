package com.mmugur81.model;

import java.awt.*;
import java.time.Period;
import java.util.Date;

/**
 * Created by Mugurel on 16.10.2016.
 * Used for order search criteria or as a filter
 */

public class OrderSearchCriteria {

    public User user;

    public Order.Status status;

    public Date created;

    public DateInterval createdBetween;

    /******************************************************************************************************************/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order.Status getStatus() {
        return status;
    }

    public void setStatus(Order.Status status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public DateInterval getCreatedBetween() {
        return createdBetween;
    }

    public void setCreatedBetween(DateInterval createdBetween) {
        this.createdBetween = createdBetween;
    }
}
