package com.mmugur81.service;

import com.mmugur81.REST_model.RestCategory;
import com.mmugur81.REST_model.RestOrder;
import com.mmugur81.REST_model.RestOrderItem;
import com.mmugur81.REST_model.RestProduct;
import com.mmugur81.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by mugurel on 15.09.2016.
 * OrderService test
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ComponentScan("com.mmugur81")
public class OrderServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    // Helper vars
    private User user;
    private Category c1;
    private Product p1;
    private Product p2;
    private Currency currency;
    private Order order;

    @Before
    public void before() {
        if (user == null) {

            // Initialize all helper variables
            user = userService.registerUser("Test", "User", "test@test.test", "testing");

            currency = Currency.EUR;

            c1 = categoryService.add("Category 1");

            p1 = new Product("Product 1", c1, user);
            p1.setPrice(new Price(33.33, currency));
            productService.save(p1);

            p2 = new Product("Product 2", c1, user);
            p2.setPrice(new Price(66.66, currency));
            productService.save(p2);

            order = orderService.createOrder(user.getId(), currency);
        }
    }

    @Test(expected = NullPointerException.class)
    public void createOrderFailTest() {
        // Given
        long userId = -100;

        // When
        Order order = orderService.createOrder(userId, null);

        // Then - NullPointerException should be expected
    }

    @Test
    public void createOrderWithDefaultCurrency() {
        // Given
        long userId = user.getId();

        // When
        Order orderDef = orderService.createOrder(userId, null);

        // Then
        assertThat(orderDef.getTotal(), hasProperty("currency", equalTo(productService.getDefaultCurrency())));
    }

    @Test
    public void createOrderWithCurrency() {
        // Given + When - order created in before()

        // Then
        assertThat(order, hasProperty("id", greaterThan(0L)));
        assertThat(order, hasProperty("user", equalTo(user)));
        assertThat(order, hasProperty("status", equalTo(Order.Status.Pending)));
        assertThat(order, hasProperty("payed", equalTo(false)));
        assertThat(order.getTotal(), hasProperty("price", equalTo(0.0)));
        assertThat(order.getTotal(), hasProperty("currency", equalTo(currency)));
    }

    @Test
    public void loadOrderTest() {
        // Given + When - order created in before()

        // When
        Order savedOrder = orderService.get(order.getId());

        // Then
        assertThat(order, hasProperty("id", equalTo(savedOrder.getId())));
        assertThat(order, hasProperty("user", equalTo(savedOrder.getUser())));
        assertThat(order.getTotal(), hasProperty("currency", equalTo(savedOrder.getTotal().getCurrency())));
    }

    @Test
    public void addProductItemTest() {
        // Given - order created in before()

        // When - adding 2 products
        orderService.addProductItem(order.getId(), p1.getId());
        orderService.addProductItem(order.getId(), p2.getId());

        // Then
        double expectedPrice = p1.getPriceValue() + p2.getPriceValue();
        List<OrderItem> orderItems = order.getOrderItems();
        List<Product> products = new ArrayList<>();
        products.addAll(orderItems.stream().map(OrderItem::getProduct).collect(Collectors.toList()));

        assertThat(order.getTotal(), hasProperty("price", equalTo(expectedPrice)));
        assertThat(orderItems, hasSize(2));
        assertThat(products, hasItem(p1));
        assertThat(products, hasItem(p2));
    }


    @Test
    public void removeProductItemSuccessfulTest() {
        // Given - order created in before()

        // Add 2 products
        short i1 = orderService.addProductItem(order.getId(), p1.getId());
        short i2 = orderService.addProductItem(order.getId(), p2.getId());

        // When - removing first product
        boolean success = orderService.removeProductItem(order.getId(), i1);

        // Then
        double expectedPrice = p2.getPriceValue();
        List<OrderItem> orderItems = order.getOrderItems();
        List<Product> products = new ArrayList<>();
        products.addAll(orderItems.stream().map(OrderItem::getProduct).collect(Collectors.toList()));

        Assert.assertTrue(success);
        assertThat(order.getTotal(), hasProperty("price", equalTo(expectedPrice)));
        assertThat(orderItems, hasSize(1));
        assertThat(products, not(hasItem(p1)));
        assertThat(products, hasItem(p2));
    }

    @Test
    public void removeProductItemFailTest() {
        // Given - order created in before()

        // Add only one product
        short i1 = orderService.addProductItem(order.getId(), p1.getId());

        // When - removing non existent product
        boolean success = orderService.removeProductItem(order.getId(), (short) 50);

        // Then
        double expectedPrice = p1.getPriceValue();
        List<OrderItem> orderItems = order.getOrderItems();
        List<Product> products = new ArrayList<>();
        products.addAll(orderItems.stream().map(OrderItem::getProduct).collect(Collectors.toList()));

        Assert.assertFalse(success);
        assertThat(order.getTotal(), hasProperty("price", equalTo(expectedPrice)));
        assertThat(orderItems, hasSize(1));
        assertThat(products, hasItem(p1));
    }

    @Test
    public void confirmOrderTest() {
        // Given - order created in before()

        // When
        orderService.confirmOrder(order.getId());

        // Then
        assertThat(order, hasProperty("status", equalTo(Order.Status.Confirmed)));
    }

    @Test
    public void cancelOrderTest() {
        // Given - order created in before()

        // When
        orderService.cancelOrder(order.getId());

        // Then
        assertThat(order, hasProperty("status", equalTo(Order.Status.Cancelled)));
    }

    @Test
    public void registerPaymentTest() {
        // Given - order created in before()

        // When
        Date now = new Date();
        orderService.registerPayment(order.getId());

        // Then
        assertThat(order, hasProperty("payed", equalTo(true)));
        assertThat(order, hasProperty("payDate", greaterThanOrEqualTo(now)));
    }

    @Test
    public void getRestOrderTest() {
        // Given - order created in before(); also add 2 products
        orderService.addProductItem(order.getId(), p1.getId());
        orderService.addProductItem(order.getId(), p2.getId());

        // When
        RestOrder restOrder = order.getRestOrder();

        // Then
        assertThat(restOrder, hasProperty("id", equalTo(order.getId())));
        assertThat(restOrder, hasProperty("userId", equalTo(order.getUser().getId())));
        assertThat(restOrder, hasProperty("status", equalTo(order.getStatus())));
        assertThat(restOrder, hasProperty("notes", equalTo(order.getNotes())));
        assertThat(restOrder, hasProperty("payed", equalTo(order.isPayed())));
        assertThat(restOrder, hasProperty("payDate", equalTo(order.getPayDate())));

        // Check total
        Price total = restOrder.getTotal();
        assertThat(total, hasProperty("price", equalTo(order.getTotal().getPrice())));
        assertThat(total, hasProperty("currency", equalTo(order.getTotal().getCurrency())));

        // Check items
        List<RestOrderItem> orderItems = restOrder.getOrderItems();
        List<RestProduct> products = new ArrayList<>();
        products.addAll(orderItems.stream().map(RestOrderItem::getProduct).collect(Collectors.toList()));
        RestProduct rp1 = orderItems.get(0).getProduct();
        RestProduct rp2 = orderItems.get(1).getProduct();

        assertThat(orderItems, hasSize(2));
        assertThat(products, hasItem(rp1));
        assertThat(products, hasItem(rp2));

        // p1
        assertThat(rp1, hasProperty("id", equalTo(p1.getId())));
        assertThat(rp1, hasProperty("name", equalTo(p1.getName())));
        // p1 - category
        RestCategory rp1Categ = rp1.getCategory();
        assertThat(rp1Categ, hasProperty("id", equalTo(p1.getCategory().getId())));
        assertThat(rp1Categ, hasProperty("name", equalTo(p1.getCategory().getName())));
        //p1 - price
        Price rp1Price = rp1.getPrice();
        assertThat(rp1Price, hasProperty("price", equalTo(p1.getPriceValue())));
        assertThat(rp1Price, hasProperty("currency", equalTo(p1.getPrice().getCurrency())));

        // p2
        assertThat(rp2, hasProperty("id", equalTo(p2.getId())));
        assertThat(rp2, hasProperty("name", equalTo(p2.getName())));
        // p2 - category
        RestCategory rp2Categ = rp2.getCategory();
        assertThat(rp1Categ, hasProperty("id", equalTo(p2.getCategory().getId())));
        assertThat(rp1Categ, hasProperty("name", equalTo(p2.getCategory().getName())));
        //p1 - price
        Price rp2Price = rp2.getPrice();
        assertThat(rp2Price, hasProperty("price", equalTo(p2.getPriceValue())));
        assertThat(rp2Price, hasProperty("currency", equalTo(p2.getPrice().getCurrency())));
    }

    // Fail tests for invalid order id ---------------------------------------------------------------------------------

    @Test
    public void failLoadOrderTest() {
        Order loadedOrder = orderService.get(100L);
        Assert.assertNull(loadedOrder);
    }

    @Test(expected = NullPointerException.class)
    public void failCreateOrderTest() {
        Order savedOrder = orderService.createOrder(100L, null);
    }

    @Test(expected = NullPointerException.class)
    public void failAddProductItemOnOrderIdTest() {
        orderService.addProductItem(100L, p1.getId());
    }

    @Test(expected = NullPointerException.class)
    public void failAddProductItemOnProductIdTest() {
        orderService.addProductItem(order.getId(), 100L);
    }

    @Test(expected = NullPointerException.class)
    public void failRemoveProductItemTest() {
        orderService.removeProductItem(100L, (short) p1.getId());
    }

    @Test(expected = NullPointerException.class)
    public void failConfirmOrderTest() {
        orderService.confirmOrder(100L);
    }

    @Test(expected = NullPointerException.class)
    public void failCancelOrderTest() {
        orderService.cancelOrder(100L);
    }

    @Test(expected = NullPointerException.class)
    public void failRegisterPaymentTest() {
        orderService.registerPayment(100L);
    }
}