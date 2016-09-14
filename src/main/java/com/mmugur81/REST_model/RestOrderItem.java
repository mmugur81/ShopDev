package com.mmugur81.REST_model;

import com.mmugur81.model.Price;

/**
 * Created by Mugurel on 14.09.2016.
 * REST OrderItem model
 */

public class RestOrderItem {

    private short itemNumber;

    private RestProduct product;

    private Price price;

    /******************************************************************************************************************/

    public RestOrderItem() {
    }

    public RestOrderItem(short itemNumber, RestProduct product, Price price) {
        this.itemNumber = itemNumber;
        this.product = product;
        this.price = price;
    }

    /******************************************************************************************************************/

    public short getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(short itemNumber) {
        this.itemNumber = itemNumber;
    }

    public RestProduct getProduct() {
        return product;
    }

    public void setProduct(RestProduct product) {
        this.product = product;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
