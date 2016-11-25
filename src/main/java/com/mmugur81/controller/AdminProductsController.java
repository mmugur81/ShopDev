package com.mmugur81.controller;

import com.mmugur81.model.Currency;
import com.mmugur81.model.Product;
import com.mmugur81.model.User;
import com.mmugur81.service.CategoryService;
import com.mmugur81.service.ProductService;
import com.mmugur81.service.UploadService;
import com.mmugur81.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mugurel on 02.09.2016.
 * Controller for Products administration
 */

@Controller
@RequestMapping("/admin/product")
public class AdminProductsController {

    private ProductService productService;
    private CategoryService categoryService;
    private UserService userService;
    private UploadService uploadService;
    private MessageSource ms;

    @Autowired
    public AdminProductsController(
            ProductService productService,
            CategoryService categoryService,
            UserService userService,
            UploadService uploadService,
            MessageSource ms
    ) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.uploadService = uploadService;
        this.ms = ms;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "/admin/product/list";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long id) {
        Product product = productService.get(id);

        if (product == null) {
            return "redirect:/admin/product/?err_del=1";
        } else {
            productService.delete(product);
            return "redirect:/admin/product/";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {

        model.addAttribute("pageTitle", ms.getMessage("admin.product.add.title", null, request.getLocale()));
        model.addAttribute("lblSubmit", ms.getMessage("admin.product.add.submit", null, request.getLocale()));
        model.addAttribute("productForm", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("currencies", Currency.values());

        return "/admin/product/edit";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(
            @Valid @ModelAttribute("productForm") Product product,
            BindingResult bindingResult,
            Model model,
            @RequestParam("productImage") MultipartFile uploadedImage,
            HttpServletRequest request
    ) {
        Boolean hasErrors = false;
        Product addedProduct = null;
        User currentUser = userService.getAuthenticatedUser();

        if (bindingResult.hasErrors()) {
            hasErrors = true;
        } else {
            addedProduct = productService.save(product, currentUser);
        }

        if (addedProduct == null) {
            // Set some error
            bindingResult.addError(new ObjectError("productForm", "Could not save to DB"));
            hasErrors = true;
        }

        if (hasErrors) {
            model.addAttribute("pageTitle", ms.getMessage("admin.product.add.title", null, request.getLocale()));
            model.addAttribute("lblSubmit", ms.getMessage("admin.product.add.submit", null, request.getLocale()));
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("currencies", Currency.values());

            return "/admin/product/edit";
        } else {
            // Process image upload
            uploadService.uploadFile(
                uploadedImage, addedProduct.getId(), UploadService.Target.PRODUCT, UploadService.Type.IMAGE);

            return "redirect:/admin/product/";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id, HttpServletRequest request) {

        Product product = productService.get(id);
        if (product == null) {
            return "/admin/product/?err_id=1";
        }

        model.addAttribute("pageTitle", ms.getMessage("admin.product.edit.title", null, request.getLocale()));
        model.addAttribute("lblSubmit", ms.getMessage("admin.product.edit.submit", null, request.getLocale()));
        model.addAttribute("productForm", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("currencies", Currency.values());

        // Check product image
        String productImage = uploadService.retrieveFileName(UploadService.Target.PRODUCT, id);
        if (productImage != null) {
            productImage = "/media/" + UploadService.Target.PRODUCT + "/" + id;
        }
        model.addAttribute("productImage", productImage);

        return "/admin/product/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(
            @PathVariable("id") Long id,
            @ModelAttribute("categoryForm") @Valid Product product,
            BindingResult bindingResult,
            Model model,
            @RequestParam("productImage") MultipartFile uploadedImage,
            HttpServletRequest request
    ) {
        Boolean hasErrors = false;
        Product savedProduct = null;

        if (bindingResult.hasErrors()) {
            hasErrors = true;
        } else {
            savedProduct = productService.save(product);
        }

        if (savedProduct == null) {
            // Set some error
            bindingResult.addError(new ObjectError("productForm", "Could not save to DB"));
            hasErrors = true;
        }

        if (hasErrors) {
            model.addAttribute("pageTitle", ms.getMessage("admin.product.edit.title", null, request.getLocale()));
            model.addAttribute("lblSubmit", ms.getMessage("admin.product.edit.submit", null, request.getLocale()));
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("currencies", Currency.values());

            return "/admin/product/edit";
        }
        else {
            // Process image removal
            Boolean removeImage = Boolean.valueOf(request.getParameter("removeImage"));
            if (removeImage) {
                uploadService.removeFile(savedProduct.getId(), UploadService.Target.PRODUCT, UploadService.Type.IMAGE);
            }

            // Process image upload
            uploadService.uploadFile(
                uploadedImage, savedProduct.getId(), UploadService.Target.PRODUCT, UploadService.Type.IMAGE);

            return "redirect:/admin/product/";
        }
    }
}
