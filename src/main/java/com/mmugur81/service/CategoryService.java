package com.mmugur81.service;

import com.mmugur81.model.Category;

import java.util.List;

/**
 * Created by mugurel on 01.11.2016.
 */
public interface CategoryService {

    Category add(String name);

    Category add(String name, Category parentCategory);

    Category add(String name, long parentCategoryId);

    Category save(Category category);

    Category get(long id);

    List<Category> getAllCategories();

    void delete(long id);

    // Redundant
    List<Category> getAllSubCategories(long id);
}
