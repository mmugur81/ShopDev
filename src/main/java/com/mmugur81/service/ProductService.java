package com.mmugur81.service;

import com.mmugur81.model.Category;
import com.mmugur81.model.Price;
import com.mmugur81.model.Product;
import com.mmugur81.model.User;
import com.mmugur81.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mugurel on 16.08.2016.
 * Product Service
 */

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    public Product createProduct(String title, Category category, User user) {
        Product product = new Product(title, category, user);
        return productRepo.saveAndFlush(product);
    }

    public Product createProduct(String title, long categoryId, long userId, double price) {
        Category category = categoryService.get(categoryId);
        User user = userService.get(userId);
        Product product = new Product(title, category, user);
        Price p = new Price(price);
        product.setPrice(p);
        return productRepo.saveAndFlush(product);
    }

    public Product get(long id) {
        return productRepo.findOne(id);
    }
}
