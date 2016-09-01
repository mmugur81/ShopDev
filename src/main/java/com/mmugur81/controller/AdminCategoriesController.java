package com.mmugur81.controller;

import com.mmugur81.model.Category;
import com.mmugur81.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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
    private MessageSource ms;

    @Autowired
    public AdminCategoriesController(CategoryService categoryService, MessageSource ms) {
        this.categoryService = categoryService;
        this.ms = ms;
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
    public String add(Model model, HttpServletRequest request) {
        model.addAttribute("pageTitle", ms.getMessage("admin.category.add.title", null, request.getLocale()));
        model.addAttribute("lblSubmit", ms.getMessage("admin.category.add.submit", null, request.getLocale()));
        model.addAttribute("categoryForm", new Category());
        model.addAttribute("categories", categoryService.getAllCategories());

        return "/admin/category/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String add(
       @ModelAttribute("categoryForm") @Valid Category category,
       BindingResult bindingResult,
       Model model,
       HttpServletRequest request
    ) {
        Boolean hasErrors = false;
        Category addedCategory = null;

        if (bindingResult.hasErrors()) {
            hasErrors = true;
        } else {
            addedCategory = categoryService.save(category);
        }

        if (addedCategory == null) {
            // Set some error
            bindingResult.addError(new ObjectError("categoryForm", "Could not save to DB"));
            hasErrors = true;
        }

        if (hasErrors) {
            model.addAttribute("pageTitle", ms.getMessage("admin.category.add.title", null, request.getLocale()));
            model.addAttribute("lblSubmit", ms.getMessage("admin.category.add.submit", null, request.getLocale()));
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/admin/category/edit";
        }
        else {
            return "redirect:/admin/category/";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        Category category = categoryService.get(id);
        if (category == null) {
            return "redirect:/admin/category/?err_id=1";
        }

        model.addAttribute("pageTitle", ms.getMessage("admin.category.edit.title", null, request.getLocale()));
        model.addAttribute("lblSubmit", ms.getMessage("admin.category.edit.submit", null, request.getLocale()));
        model.addAttribute("categoryForm", category);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "/admin/category/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(
        @PathVariable("id") Long id,
        @ModelAttribute("categoryForm") @Valid Category category,
        BindingResult bindingResult,
        Model model,
        HttpServletRequest request
    ) {
        Boolean hasErrors = false;
        Category savedCategory = null;

        if (id != category.getId()) {
            bindingResult.addError(new ObjectError("categoryForm", "Category ID doesn't match URL's id"));
            hasErrors = true;
        } else {
            savedCategory = categoryService.save(category);
        }

        if (savedCategory == null) {
            // Set some error
            bindingResult.addError(new ObjectError("categoryForm", "Could not save to DB"));
            hasErrors = true;
        }

        if (hasErrors) {
            model.addAttribute("pageTitle", ms.getMessage("admin.category.edit.title", null, request.getLocale()));
            model.addAttribute("lblSubmit", ms.getMessage("admin.category.edit.submit", null, request.getLocale()));
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/admin/category/edit/" + id;
        }
        else {
            return "redirect:/admin/category/";
        }
    }
}
