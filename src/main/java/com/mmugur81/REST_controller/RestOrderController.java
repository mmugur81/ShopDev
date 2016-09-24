package com.mmugur81.REST_controller;

import com.mmugur81.REST_model.RestOrder;
import com.mmugur81.REST_model.RestResponse;
import com.mmugur81.model.Order;
import com.mmugur81.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Mugurel on 14.09.2016.
 * REST controller for orders
 */

@RestController
@RequestMapping("/api/order")
public class RestOrderController {

    private OrderService orderService;

    @Autowired
    public RestOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RestResponse<RestOrder> get(@PathVariable("id") long id) {
        RestResponse<RestOrder> resp = new RestResponse<>();
        Order order = this.orderService.get(id);

        if (order == null) {
            resp.setSuccess(false);
            resp.addError(String.format("Order with id [%d] could not be found!", id));
        } else {
            resp.setSuccess(true);
            resp.setData(order.getRestOrder());
        }

        return resp;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RestResponse<RestOrder> put(
        @Valid @RequestBody final RestOrder restOrder,
        BindingResult bindingResult
    ) {
        RestResponse<RestOrder> resp = new RestResponse<>();

        if (bindingResult.hasErrors()) {
            resp.setSuccess(false);
            for (ObjectError error : bindingResult.getAllErrors()) {
                resp.addError(error.toString());
            }
        }
        else {
            resp.setSuccess(true);
            resp.setData(restOrder);
        }

        return resp;
    }
}
