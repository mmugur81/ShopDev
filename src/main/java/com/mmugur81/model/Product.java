package com.mmugur81.model;

import com.mmugur81.REST_model.RestProduct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Mugurel on 16.08.2016.
 * Product Model
 */

@Entity
@Table(name = "products")
public class Product extends BaseModel {

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_category", foreignKey = @ForeignKey(name = "fk_products_categories_id"))
    private Category category;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_products_users_id"))
    private User user;

    @Size(min=3, max=30)
    @Column(length = 30)
    private String name;

    @Lob
    private String description;

    @Embedded
    private Price price;

    /******************************************************************************************************************/

    public Product() { }

    public Product(String name, Category category, User user) {
        this.name = name;
        this.category = category;
        this.user = user;
    }

    public RestProduct getRestProduct() {
        return new RestProduct(
            this.getId(),
            this.category.getRestCategory(),
            this.name,
            this.price
        );
    }

    /******************************************************************************************************************/

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
