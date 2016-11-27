package com.mmugur81.REST_model;

import com.mmugur81.model.Price;

/**
 * Created by Mugurel on 14.09.2016.
 * REST Product model
 */

public class RestProduct {

    private long id;

    private RestCategory category;

    private String name;

    private Price price;

    private String imageUrl;

    /******************************************************************************************************************/

    public RestProduct() {
    }

    public RestProduct(long id, RestCategory category, String name, Price price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    /******************************************************************************************************************/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RestCategory getCategory() {
        return category;
    }

    public void setCategory(RestCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
