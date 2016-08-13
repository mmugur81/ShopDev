package com.mmugur81.repository;

import com.mmugur81.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Mugurel on 13.08.2016.
 * Category Repository
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    public List<Category> findByParentCategory(Category parentCategory);
}
