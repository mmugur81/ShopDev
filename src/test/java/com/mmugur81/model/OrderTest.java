package com.mmugur81.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by mugurel on 14.09.2016.
 * Order model business logic test
 */

public class OrderTest {

    private static User user;
    private static Category category;

    @BeforeClass
    public static void beforeClass() {
        user = new User("Test", "User", "test@test.test", "testing");
        category = new Category("Test Category");
    }

    @Test
    public void newOrderTest() {
        // Given + When
        Order order = new Order(user, Currency.EUR);

        // Then
        assertThat(order, hasProperty("user", equalTo(user)));
        assertThat(order, hasProperty("status", equalTo(Order.Status.Pending)));
        assertThat(order, hasProperty("payed", equalTo(false)));
        assertThat(order.getTotal(), hasProperty("price", equalTo(0.0)));
        assertThat(order.getTotal(), hasProperty("currency", equalTo(Currency.EUR)));
    }

    @Test
    public void addGoodProductItemTest() {
        // Given
        Order order = new Order(user, Currency.EUR);

        Product p1 = new Product("Product 1", category, user);
        p1.setPrice(new Price(100, Currency.EUR));

        Product p2 = new Product("Product 2", category, user);
        p2.setPrice(new Price(54.99, Currency.EUR));

        // When
        order.addProductItem(p1);
        order.addProductItem(p2);

        // Then
        List<OrderItem> orderItems = order.getOrderItems();

        List<Product> products = new ArrayList<>();
        // extract products from orderItems
        products.addAll(orderItems.stream().map(OrderItem::getProduct).collect(Collectors.toList()));

        assertThat(orderItems, hasSize(2));
        assertThat(products, hasItem(p1));
        assertThat(products, hasItem(p2));
        assertThat(order.getTotal(), hasProperty("price", equalTo(154.99)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addWrongCurrencyProductItem() {
        // Given
        Order order = new Order(user, Currency.EUR);

        Product p1 = new Product("Product 1", category, user);
        p1.setPrice(new Price(100, Currency.USD));

        // When
        order.addProductItem(p1);

        // Then IllegalArgumentException should be expected
    }

    @Test
    public void removeGoodProductItemTest() {
        // Given
        Order order = new Order(user, Currency.EUR);

        Product p1 = new Product("Product 1", category, user);
        p1.setPrice(new Price(100, Currency.EUR));
        Short i1 = order.addProductItem(p1);

        Product p2 = new Product("Product 2", category, user);
        p2.setPrice(new Price(54.99, Currency.EUR));
        order.addProductItem(p2);

        // When
        Boolean removedOk = order.removeProductItem(i1);

        // Then
        List<OrderItem> orderItems = order.getOrderItems();

        List<Product> products = new ArrayList<>();
        // extract products from orderItems
        products.addAll(orderItems.stream().map(OrderItem::getProduct).collect(Collectors.toList()));

        Assert.assertTrue(removedOk);
        assertThat(orderItems, hasSize(1));
        assertThat(products, not(hasItem(p1)));
        assertThat(products, hasItem(p2));
        assertThat(order.getTotal(), hasProperty("price", equalTo(p2.getPrice().getPrice())));
    }

    @Test
    public void removeWrongProductItemTest() {
        // Given
        Order order = new Order(user, Currency.EUR);

        Product p1 = new Product("Product 1", category, user);
        p1.setPrice(new Price(100, Currency.EUR));
        Short i1 = order.addProductItem(p1);

        // When
        Boolean removedOk = order.removeProductItem((short) 5);

        // Then
        List<OrderItem> orderItems = order.getOrderItems();

        List<Product> products = new ArrayList<>();
        // extract products from orderItems
        products.addAll(orderItems.stream().map(OrderItem::getProduct).collect(Collectors.toList()));

        Assert.assertFalse(removedOk);
        assertThat(orderItems, hasSize(1));
        assertThat(products, hasItem(p1));
        assertThat(order.getTotal(), hasProperty("price", equalTo(p1.getPrice().getPrice())));
    }

    @Test
    public void registerPaymentTest() {
        // Given
        Order order = new Order(user, Currency.EUR);

        // When
        order.registerPayment();

        // Then
        assertThat(order, hasProperty("payed", equalTo(true)));
        assertThat(order, hasProperty("payDate", notNullValue()));
    }
}
