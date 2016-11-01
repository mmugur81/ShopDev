package com.mmugur81.service;

import com.mmugur81.model.Category;
import com.mmugur81.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mugurel on 13.08.2016.
 * Category Service
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    /******************************************************************************************************************/

    @Override
    public Category add(String name) {
        Category category = new Category(name);
        return categoryRepo.saveAndFlush(category);
    }

    @Override
    public Category add(String name, Category parentCategory) {
        Category category = new Category(name, parentCategory);
        return categoryRepo.saveAndFlush(category);
    }

    @Override
    public Category add(String name, long parentCategoryId) {
        Category parentCategory = categoryRepo.findOne(parentCategoryId);
        Category category = new Category(name, parentCategory);
        return categoryRepo.saveAndFlush(category);
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.saveAndFlush(category);
    }

    @Override
    public Category get(long id) {
        return categoryRepo.findOne(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public void delete(long id) {
        categoryRepo.delete(id);
    }

    // Redundant
    @Override
    public List<Category> getAllSubCategories(long id) {
        Category parentCategory = categoryRepo.findOne(id);
        List<Category> categories = categoryRepo.findByParentCategory(parentCategory);

        return categories;
    }
}
