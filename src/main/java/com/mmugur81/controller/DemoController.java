package com.mmugur81.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mugurel on 09.08.2016.
 */

@Controller
public class DemoController {

    @RequestMapping(value = "/demo")
    public String sayDemo(Model model) {
        model.addAttribute("greeting", "Hey world!");
        System.out.println("Touched Demo controller - sayDemo action");

        return "hello";
    }
}
