package com.mmugur81.REST_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mugurel on 24.09.2016.
 * REST response object wrapper
 */
public class RestResponse<T> {

    private boolean success;

    private List<String> errors;

    private T data;

    /******************************************************************************************************************/

    public RestResponse() {
        this.errors = new ArrayList<>();
    }

    public RestResponse(boolean success) {
        this();
        this.success = success;
    }

    /******************************************************************************************************************/

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String errMsg) {
        this.errors.add(errMsg);
    }
}
