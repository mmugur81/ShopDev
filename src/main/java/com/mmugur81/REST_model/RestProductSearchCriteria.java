package com.mmugur81.REST_model;

import com.mmugur81.model.Currency;
import com.mmugur81.model.DoubleInterval;

/**
 * Created by Mugurel on 27.11.2016.
 * Used on API for product search criteria
 */

public class RestProductSearchCriteria {

    public Long categoryId;

    public String nameLike;

    public Currency currency;

    public DoubleInterval priceBetween;

}
