package com.mmugur81.controller;

import com.mmugur81.model.User;
import com.mmugur81.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mugurel on 09.08.2016.
 */

@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/demo")
    public String sayDemo(Model model) {
        model.addAttribute("greeting", "Hey world!");
        System.out.println("Touched Demo controller - sayDemo action");

        User user = userService.registerUser("Mugurel", "ceva@nimic.lol", User.Type.Admin);

        return "hello";
    }
}
