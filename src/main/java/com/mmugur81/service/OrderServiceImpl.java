package com.mmugur81.service;

import com.mmugur81.REST_model.RestOrder;
import com.mmugur81.REST_model.RestOrderItem;
import com.mmugur81.model.*;
import com.mmugur81.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mugurel on 13.09.2016.
 * Order Service
 */

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private UserService userService;

    private ProductService productService;

    @Autowired
    public OrderServiceImpl(
        OrderRepository orderRepository,
        UserService userService,
        ProductService productService
    ) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
    }

    /******************************************************************************************************************/

    @Override
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

    @Override
    public Order get(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public short addProductItem(Long orderId, Long productId) {
        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            throw new NullPointerException("Order with id "+orderId+" not found!");
        }

        Product product = productService.get(productId);
        if (product == null) {
            throw new NullPointerException("Product with id "+productId+" not found!");
        }

        short itemNumber = order.addProductItem(product);
        orderRepository.saveAndFlush(order);

        return itemNumber;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public boolean removeProductItem(Long orderId, short itemNumber) {
        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            throw new NullPointerException("Order with id "+orderId+" not found!");
        }

        boolean success = order.removeProductItem(itemNumber);
        if (success) {
            orderRepository.saveAndFlush(order);
        }

        return success;
    }

    @Override
    public void confirmOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            throw new NullPointerException("Order with id "+orderId+" not found!");
        }

        order.setStatus(Order.Status.Confirmed);
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            throw new NullPointerException("Order with id "+orderId+" not found!");
        }

        order.setStatus(Order.Status.Cancelled);
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void registerPayment(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            throw new NullPointerException("Order with id "+orderId+" not found!");
        }

        order.registerPayment();
        orderRepository.saveAndFlush(order);
    }

    @Override
    public Order convertFromRestOrder(RestOrder restOrder) {
        // It assumes restOrder has already been validated
        Order order = new Order();
        order.setUser(userService.get(restOrder.getUserId()));

        // Add products
        Product product;
        List<RestOrderItem> orderItems = restOrder.getOrderItems();
        for (RestOrderItem item : orderItems) {
            product = productService.get(item.getProduct().getId());
            order.addProductItem(product);
        }

        return order;
    }

    // TODO add testing for this method
    @Override
    public List<Order> searchByCriteria(final OrderSearchCriteria orderSearch) {
        List result = orderRepository.findAll(OrderSpecs.bySearchCriteria(orderSearch));

        return result;
    }
}
