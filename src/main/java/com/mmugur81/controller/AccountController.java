package com.mmugur81.controller;

import com.mmugur81.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mugurel on 19.08.2016.
 * Account controller
 */

@Controller
@RequestMapping("/account")
public class AccountController {

    private UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login")
    public String login(Model model, String error, String logout, HttpServletRequest request) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "/account/login";
    }

    @RequestMapping(value = "/")
    public String index() {
        return "/account/index";
    }
}
