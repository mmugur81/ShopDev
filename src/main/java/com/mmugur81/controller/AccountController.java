package com.mmugur81.controller;

import com.mmugur81.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
    private MessageSource ms;

    @Autowired
    public AccountController(UserService userService, MessageSource ms) {
        this.userService = userService;
        this.ms = ms;
    }

    @RequestMapping(value = "/login")
    public String login(Model model, String error, String logout, HttpServletRequest request) {
        if (error != null) {
            String msg = ms.getMessage("account.login.error_invaild_password", null, request.getLocale());
            model.addAttribute("error", msg);
        }

        if (logout != null) {
            String msg = ms.getMessage("account.login.info_logout", null, request.getLocale());
            model.addAttribute("message", msg);
        }

        return "/account/login";
    }

    @RequestMapping(value = "/")
    public String index() {
        return "/account/index";
    }
}
