package com.mmugur81.controller;

import com.mmugur81.model.Category;
import com.mmugur81.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by mugurel on 30.08.2016.
 * Controller for Categories admin
 */

@Controller
@RequestMapping("/admin/category")
public class AdminCategoriesController {

    private CategoryService categoryService;

    @Autowired
    public AdminCategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "/admin/category/list";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id) {
        Category cat = categoryService.get(id);

        if (cat == null) {
            return "redirect:/admin/category/?err_del=1";
        } else {
            categoryService.delete(id);
            return "redirect:/admin/category/";
        }
    }
}
