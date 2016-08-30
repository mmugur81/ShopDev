package com.mmugur81.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mugurel on 30.08.2016.
 * Admin controller
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/")
    public String index() {
        return "/admin/index";
    }
}
