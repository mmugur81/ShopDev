package com.mmugur81.service;

import com.mmugur81.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by mugurel on 07.09.2016.
 * CategoryService test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ComponentScan("com.mmugur81")
public class CategoryServiceTest {

    private final String catName = "Test Category";
    private final String catDesc = "Some random description";
    private final String parentCatName = "Parent Category";

    @Autowired
    private CategoryService categoryService;

    @Test
    public void addSimpleCategoryTest() {
        // Given
        Category category = new Category(catName);

        // When
        Category addedCategory = categoryService.save(category);

        // Then
        assertThat(addedCategory, hasProperty("name", equalTo(catName)));
        assertThat(addedCategory, hasProperty("id", greaterThan(0L)));
        assertThat(addedCategory, hasProperty("parentCategory", equalTo(null)));
    }

    @Test
    public void addNewCategoryTest() {
        // Given
        Category parentCategory = new Category(parentCatName);
        Category category = new Category(catName);
        category.setDescription(catDesc);
        category.setParentCategory(parentCategory);

        // When
        Category addedCategory = categoryService.save(category);

        // Then
        assertThat(addedCategory, hasProperty("id", greaterThan(0L)));
        assertThat(addedCategory, hasProperty("name", equalTo(category.getName())));
        assertThat(addedCategory, hasProperty("description", equalTo(category.getDescription())));
        assertThat(addedCategory, hasProperty("parentCategory", equalTo(category.getParentCategory())));
    }

    @Test
    public void updateCategoryTest() {
        // Given
        Category parentCategory = new Category(parentCatName);

        Category category = new Category(catName);
        category.setDescription(catDesc);
        category.setParentCategory(parentCategory);

        // When
        Category savedCategory = categoryService.save(category);
        Category savedParent = savedCategory.getParentCategory();

        // Then
        assertThat(savedCategory, hasProperty("id", notNullValue()));
        assertThat(savedCategory, hasProperty("name", equalTo(category.getName())));
        assertThat(savedCategory, hasProperty("description", equalTo(category.getDescription())));

        assertThat(savedParent, notNullValue());
        assertThat(savedParent, hasProperty("id", notNullValue()));
        assertThat(savedParent, hasProperty("name", equalTo(category.getParentCategory().getName())));
    }


    @Test
    public void getCategoryTest() {
        // Given
        Category parentCategory = new Category(parentCatName);
        Category category = new Category(catName);
        category.setDescription(catDesc);
        category.setParentCategory(parentCategory);

        // When
        Category savedCategory = categoryService.save(category);
        Category loadedCategory = categoryService.get(savedCategory.getId());

        // Then
        assertThat(savedCategory, equalTo(loadedCategory));
    }

    @Test
    public void getAllCategoriesTest() {
        // Given
        Category cat1 = categoryService.save(new Category(parentCatName));
        Category cat2 = categoryService.save(new Category(catName));

        // When
        List<Category> categories = categoryService.getAllCategories();

        // Then
        assertThat(categories, hasItem(cat1));
        assertThat(categories, hasItem(cat2));
    }

    @Test
    public void deleteCategoryTest() {
        // Given
        Category cat1 = categoryService.save(new Category(catName));

        // When
        categoryService.delete(cat1.getId());
        Category cat2 = categoryService.get(cat1.getId());

        // Then
        assertThat(cat2, nullValue());
    }
}
