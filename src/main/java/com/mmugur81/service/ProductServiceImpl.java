package com.mmugur81.service;

import com.mmugur81.model.*;
import com.mmugur81.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mugurel on 16.08.2016.
 * Product Service
 */

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;

    private CategoryService categoryService;

    private UserService userService;

    private Currency defaultCurrency;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepo,
            CategoryService categoryService,
            UserService userService,
            @Value("${app.currency}") Currency defaultCurrency
    ) {
        this.productRepo = productRepo;
        this.categoryService = categoryService;
        this.userService = userService;
        this.defaultCurrency = defaultCurrency;
    }

    @Override
    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    /******************************************************************************************************************/

    @Override
    public Product createProduct(String title, Category category, User user) {
        Product product = new Product(title, category, user);
        return productRepo.saveAndFlush(product);
    }

    @Override
    public Product createProduct(String title, long categoryId, long userId, double price) {
        Category category = categoryService.get(categoryId);
        User user = userService.get(userId);
        Product product = new Product(title, category, user);
        Price p = new Price(price, this.defaultCurrency);
        product.setPrice(p);
        return productRepo.saveAndFlush(product);
    }

    @Override
    public Product get(long id) {
        return productRepo.findOne(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getAllProducts(Category category) {
        return productRepo.findByCategory(category);
    }

    @Override
    public Product save(Product product) {
        return productRepo.saveAndFlush(product);
    }

    @Override
    public Product save(Product product, User user) {
        if (product.getUser() == null) {
            product.setUser(user);
        }
        return productRepo.saveAndFlush(product);
    }

    @Override
    public void delete(Product product) {
        productRepo.delete(product);
    }
}
