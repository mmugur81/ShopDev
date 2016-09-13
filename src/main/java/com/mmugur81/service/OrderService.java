package com.mmugur81.service;

import com.mmugur81.model.Currency;
import com.mmugur81.model.Order;
import com.mmugur81.model.Product;
import com.mmugur81.model.User;
import com.mmugur81.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mugurel on 13.09.2016.
 * Order Service
 */

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private UserService userService;

    private ProductService productService;

    @Autowired
    public OrderService(
        OrderRepository orderRepository,
        UserService userService,
        ProductService productService
    ) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
    }

    /******************************************************************************************************************/

    public Order createOrder(Long userId, Currency currency) {
        User user = userService.get(userId);
        if (user == null) {
            throw new NullPointerException("User with id "+userId+" not found!");
        }

        if (currency == null) {
            currency = productService.getDefaultCurrency();
        }

        Order order = new Order(user, currency);
        return orderRepository.saveAndFlush(order);
    }

    public Order get(Long id) {
        return orderRepository.findOne(id);
    }

    public Order addOrderProduct(Long orderId, Long productId) {
        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            throw new NullPointerException("Order with id "+orderId+" not found!");
        }

        Product product = productService.get(productId);
        if (product == null) {
            throw new NullPointerException("Product with id "+productId+" not found!");
        }

        order.addProductItem(product);
        return orderRepository.saveAndFlush(order);
    }

    public Order save(Order order) {
        return orderRepository.saveAndFlush(order);
    }
}
