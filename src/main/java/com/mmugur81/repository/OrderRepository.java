package com.mmugur81.repository;

import com.mmugur81.model.Order;
import com.mmugur81.model.Product;
import com.mmugur81.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mugurel on 13.09.2016.
 * Order Repository
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByUser(User user);
}
