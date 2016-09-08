package com.mmugur81.service;

import com.mmugur81.model.Category;
import com.mmugur81.model.Price;
import com.mmugur81.model.Product;
import com.mmugur81.model.User;
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
    public void testAddProduct() {
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
}
