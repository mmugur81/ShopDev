package com.mmugur81.controller;

import com.mmugur81.model.Category;
import com.mmugur81.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("categoryForm", new Category());
        model.addAttribute("categories", categoryService.getAllCategoriesIdValueMap());

        return "/admin/category/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String add(
       @ModelAttribute("categoryForm") @Valid Category category,
       BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "/admin/category/edit";
        }
        Category addedCategory = categoryService.add(category);

        if (addedCategory == null) {
            // Set some error
            bindingResult.addError(new ObjectError("categoryForm", "Could not save to DB"));
            return "/admin/category/edit";
        }
        else {
            return "redirect:/admin/category/";
        }
    }
}
