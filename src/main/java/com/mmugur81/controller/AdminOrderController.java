package com.mmugur81.controller;

import com.mmugur81.model.Category;
import com.mmugur81.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Mugurel on 15.10.2016.
 * Controller for Order management
 */

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    private OrderService orderService;

    @Autowired
    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {

        return "/admin/order/list";
    }
}
