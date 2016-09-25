package com.mmugur81.validator;

import com.mmugur81.REST_model.RestOrder;
import com.mmugur81.REST_model.RestOrderItem;
import com.mmugur81.REST_model.RestProduct;
import com.mmugur81.model.Product;
import com.mmugur81.model.User;
import com.mmugur81.repository.UserRepository;
import com.mmugur81.service.OrderService;
import com.mmugur81.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mugurel on 25.09.2016.
 * User exists validator
 */

public class RestOrderValidator implements ConstraintValidator<RestOrderValid, Object> {

    private OrderService orderService;
    private ProductService productService;

    @Autowired
    public RestOrderValidator(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public void initialize(RestOrderValid restOrderValid) { }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        // Assuming userId is valid and orderItems is not null ()
        boolean isValid = true;
        context.disableDefaultConstraintViolation();

        RestOrder restOrder = (RestOrder) o;
        List<RestOrderItem> orderItems = restOrder.getOrderItems();
        List<RestProduct> products = new ArrayList<>();
        products.addAll(orderItems.stream().map(RestOrderItem::getProduct).collect(Collectors.toList()));

        // Check products in orderItems
        if (products.isEmpty()) {
            isValid = false;
            context.buildConstraintViolationWithTemplate(
                    "No products found in [orderItems]")
                    .addConstraintViolation();
        }

        Product realProduct;
        for (RestProduct p : products) {
            realProduct = productService.get(p.getId());
            if (realProduct == null) {
                isValid = false;
                context.buildConstraintViolationWithTemplate(
                        String.format("Product with id [%d] not found", p.getId()))
                        .addConstraintViolation();
            }
        }

        return isValid;
    }
}
