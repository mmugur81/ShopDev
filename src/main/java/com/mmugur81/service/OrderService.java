package com.mmugur81.service;

import com.mmugur81.REST_model.RestOrder;
import com.mmugur81.model.Currency;
import com.mmugur81.model.Order;
import com.mmugur81.model.OrderSearchCriteria;

import java.util.List;

/**
 * Created by mugurel on 01.11.2016.
 */
public interface OrderService {

    Order createOrder(Long userId, Currency currency);

    Order get(Long id);

    short addProductItem(Long orderId, Long productId);

    Order save(Order order);

    boolean removeProductItem(Long orderId, short itemNumber);

    void confirmOrder(Long orderId);

    void cancelOrder(Long orderId);

    void registerPayment(Long orderId);

    Order convertFromRestOrder(RestOrder restOrder);

    // TODO add testing for this method
    List<Order> searchByCriteria(OrderSearchCriteria orderSearch);
}
