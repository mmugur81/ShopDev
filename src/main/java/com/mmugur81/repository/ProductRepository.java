package com.mmugur81.repository;

import com.mmugur81.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mugurel on 16.08.2016.
 * Product Repository
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
