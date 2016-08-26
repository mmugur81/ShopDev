package com.mmugur81.controller;

import com.mmugur81.model.*;
import com.mmugur81.service.CategoryService;
import com.mmugur81.service.ProductService;
import com.mmugur81.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mugurel on 09.08.2016.
 */

@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/demo")
    public String sayDemo(Model model) {
        model.addAttribute("greeting", "Hey world!");
        System.out.println("Touched Demo controller - sayDemo action");

        User user = userService.registerUser("Mugurel", "Mirica", "mmugur81@gmail.com", "admin");
        userService.addRole(user, UserRole.Role.ADMIN);
        System.out.println("Has role ADDMIN? "+user.hasRole(UserRole.Role.ADMIN));

        //Category cat1 = categoryService.add("Telefoane");
        //Category cat2 = categoryService.add("Samsung", cat1);
        //Category cat3 = categoryService.add("Apple", cat1);
        //Category cat4 = categoryService.add("S3", 3);

        //Category cat1 = categoryService.get(2);

        //List<Category> categories1 = cat1.getSubCategories();
        //List<Category> categories2 = categoryService.getAllSubCategories(2);

        //Product product = productService.createProduct("S1 2GB RAM", 3, 1, 50);

        //Price p = new Price(10);

        return "hello";
    }
}
