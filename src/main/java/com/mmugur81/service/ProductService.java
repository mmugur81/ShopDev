package com.mmugur81.service;

import com.mmugur81.model.*;

import java.util.List;

/**
 * Created by mugurel on 01.11.2016.
 */
public interface ProductService {
    Currency getDefaultCurrency();

    Product createProduct(String title, Category category, User user);

    Product createProduct(String title, long categoryId, long userId, double price);

    Product get(long id);

    List<Product> getAllProducts();

    List<Product> getAllProducts(Category category);

    Product save(Product product);

    Product save(Product product, User user);

    void delete(Product product);

    List<Product> searchByCriteria(ProductSearchCriteria productSearch);
}
