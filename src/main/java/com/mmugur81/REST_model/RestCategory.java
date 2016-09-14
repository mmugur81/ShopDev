package com.mmugur81.REST_model;

/**
 * Created by Mugurel on 14.09.2016.
 * REST Category Model
 */

public class RestCategory {

    private long id;

    private String name;

    /******************************************************************************************************************/

    public RestCategory() {
    }

    public RestCategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /******************************************************************************************************************/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}