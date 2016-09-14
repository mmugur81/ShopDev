package com.mmugur81.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Properties;

/**
 * Created by Mugurel on 16.08.2016.
 * Price model - embeddable object
 */

@Embeddable
public class Price {

    @Column(precision = 10, scale = 2)
    private double price;

    @Size(min = 3, max = 3)
    @Enumerated(EnumType.STRING)
    @Column(length = 3, columnDefinition = "CHAR(3)")
    private Currency currency;

    /******************************************************************************************************************/

    public Price() {}

    public Price(double price, Currency currency) {
        this.price = price;
        this.currency = currency;
    }

    /******************************************************************************************************************/

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void add(Price p) {
        if (p.currency != this.currency) {
            throw new IllegalArgumentException("Currencies don't match");
        }
        this.price += p.getPrice();
    }

    public void subtract(Price p) {
        if (p.currency != this.currency) {
            throw new IllegalArgumentException("Currencies don't match");
        }
        this.price -= p.getPrice();
        // Handle java issues with double numbers
        this.price = (double) Math.round(this.price * 100d) / 100d;
    }

}
