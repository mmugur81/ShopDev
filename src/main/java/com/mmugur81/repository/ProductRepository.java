package com.mmugur81.repository;

import com.mmugur81.model.Category;
import com.mmugur81.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Mugurel on 16.08.2016.
 * Product Repository
 */
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor {

    public List<Product> findByCategory(Category category);
}
