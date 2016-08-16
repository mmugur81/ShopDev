package com.mmugur81.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.persistence.*;
import java.util.Properties;

/**
 * Created by Mugurel on 16.08.2016.
 * Price model - embeddable object
 */

@Embeddable
public class Price {

    /* todo need to figure a way to read from application.properties
    @Transient
    @Value("${app.currency}")*/
    public static final Currency defaultCurrency = Currency.RON;

    private double price;

    @Enumerated(EnumType.STRING)
    @Column(length = 3, columnDefinition = "CHAR(3)")
    private Currency currency;

    /******************************************************************************************************************/

    public Price() {}

    public Price(double price) {
        this.price = price;
        this.currency = defaultCurrency;
    }

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

}
