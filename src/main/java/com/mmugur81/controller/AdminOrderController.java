package com.mmugur81.controller;

import com.mmugur81.model.Order;
import com.mmugur81.model.OrderSearchCriteria;
import com.mmugur81.service.OrderService;
import com.mmugur81.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    private UserService userService;

    @Autowired
    public AdminOrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(
            Model model,
            @ModelAttribute("orderSearchForm") OrderSearchCriteria searchForm,
            BindingResult bindingResult
        ) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("statuses", Order.Status.values());

        List<Order> orders = orderService.searchByCriteria(searchForm);

        return "/admin/order/list";
    }
}
