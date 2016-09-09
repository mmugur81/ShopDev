package com.mmugur81.service;

import com.mmugur81.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by mugurel on 08.09.2016.
 * ProductService Test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ComponentScan("com.mmugur81")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    private Category cat1;
    private Category cat2;
    private User user;

    @Before
    public void before() {
        cat1 = categoryService.save(new Category("Category 1"));
        cat2 = categoryService.save(new Category("Category 2"));
        user = userService.registerUser("Test", "User", "test@test.test", "testing");
    }

    @Test
    public void addProductTest() {
        // Given
        Product product = new Product("Product 1", cat1, user);
        Price price = new Price(10.50, productService.getDefaultCurrency());
        product.setPrice(price);

        // When
        Product savedProduct = productService.save(product, user);
        Price savedPrice = savedProduct.getPrice();

        // Then
        assertThat(savedProduct, hasProperty("name", equalTo(product.getName())));
        assertThat(savedProduct, hasProperty("description", equalTo(product.getDescription())));

        assertThat(savedPrice, hasProperty("price", equalTo(price.getPrice())));
        assertThat(savedPrice, hasProperty("currency", equalTo(price.getCurrency())));
    }

    @Test
    public void getProductTest() {
        // Given
        Product product = new Product("Product 1", cat1, user);
        Price price = new Price(10.50, productService.getDefaultCurrency());
        product.setPrice(price);
        product.setDescription("some random description");
        product = productService.save(product, user);

        // When
        Product loadedProduct = productService.get(product.getId());
        Price loadedPrice = loadedProduct.getPrice();

        // Then
        assertThat(loadedProduct, hasProperty("name", equalTo(product.getName())));
        assertThat(loadedProduct, hasProperty("description", equalTo(product.getDescription())));

        assertThat(loadedPrice, hasProperty("price", equalTo(price.getPrice())));
        assertThat(loadedPrice, hasProperty("currency", equalTo(price.getCurrency())));
    }

    @Test
    public void updateProductTest() {
        // Given
        Product product = new Product("Product 1", cat1, user);
        product.setPrice(new Price(10.50, productService.getDefaultCurrency()));
        product = productService.save(product, user);

        // When
        String newName = "Product Updated";
        Price newPrice = new Price(9.99, Currency.EUR);
        product.setName(newName);
        product.setPrice(newPrice);

        Product updatedProduct = productService.save(product, user);
        Price updatedPrice = updatedProduct.getPrice();

        // Then
        assertThat(updatedProduct, hasProperty("name", equalTo(newName)));

        assertThat(updatedPrice, hasProperty("price", equalTo(newPrice.getPrice())));
        assertThat(updatedPrice, hasProperty("currency", equalTo(newPrice.getCurrency())));
    }

    @Test
    public void deleteProductTest() {
        // Given
        Product product = new Product("Product 1", cat1, user);
        product.setPrice(new Price(10.50, productService.getDefaultCurrency()));
        product = productService.save(product, user);

        // When
        productService.delete(product);
        Product deletedProduct = productService.get(product.getId());

        // Then
        assertThat(deletedProduct, nullValue());
    }
}
