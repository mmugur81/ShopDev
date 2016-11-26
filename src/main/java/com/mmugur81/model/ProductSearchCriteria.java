package com.mmugur81.model;

/**
 * Created by Mugurel on 16.10.2016.
 * Used for product search criteria or as a filter
 */

public class ProductSearchCriteria {

    public Category category;

    public String nameLike;

    public Currency currency;

    public DoubleInterval priceBetween;

    /******************************************************************************************************************/

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public DoubleInterval getPriceBetween() {
        return priceBetween;
    }

    public void setPriceBetween(DoubleInterval priceBetween) {
        this.priceBetween = priceBetween;
    }
}
