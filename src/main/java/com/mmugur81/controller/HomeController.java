package com.mmugur81.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mugurel on 26.08.2016.
 * Main page controller
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home(Model model) {
        return "index";
    }

    @RequestMapping(value = "/about")
    public String about(Model model) {
        return "about";
    }

    @RequestMapping(value = "/contact")
    public String contact(Model model) {
        return "contact";
    }

}
