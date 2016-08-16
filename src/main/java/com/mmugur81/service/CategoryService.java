package com.mmugur81.service;

import com.mmugur81.model.Category;
import com.mmugur81.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mugurel on 13.08.2016.
 * Category Service
 */

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    public Category add(String name) {
        Category category = new Category(name);
        return categoryRepo.saveAndFlush(category);
    }

    public Category add(String name, Category parentCategory) {
        Category category = new Category(name, parentCategory);
        return categoryRepo.saveAndFlush(category);
    }

    public Category add(String name, long parentCategoryId) {
        Category parentCategory = categoryRepo.findOne(parentCategoryId);
        Category category = new Category(name, parentCategory);
        return categoryRepo.saveAndFlush(category);
    }

    public Category get(long id) {
        return categoryRepo.findOne(id);
    }

    // Redundant
    public List<Category> getAllSubCategories(long id) {
        Category parentCategory = categoryRepo.findOne(id);
        List<Category> categories = categoryRepo.findByParentCategory(parentCategory);

        return categories;
    }
}