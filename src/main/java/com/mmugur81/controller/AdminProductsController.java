package com.mmugur81.controller;

import com.mmugur81.model.Product;
import com.mmugur81.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by mugurel on 02.09.2016.
 * Controller for Products administration
 */

@Controller
@RequestMapping("/admin/product")
public class AdminProductsController {

    private ProductService productService;
    private MessageSource ms;

    @Autowired
    public AdminProductsController(ProductService productService, MessageSource ms) {
        this.productService = productService;
        this.ms = ms;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "/admin/product/list";
    }
}
