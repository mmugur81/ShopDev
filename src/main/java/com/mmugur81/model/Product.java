package com.mmugur81.model;

import javax.persistence.*;

/**
 * Created by Mugurel on 16.08.2016.
 * Product Model
 */

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_category", foreignKey = @ForeignKey(name = "fk_products_categories_id"))
    private Category category;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_products_users_id"))
    private User user;

    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    @Embedded
    private Price price;

    /******************************************************************************************************************/

    public Product() { }

    public Product(String title, Category category, User user) {
        this.title = title;
        this.category = category;
        this.user = user;
    }

    /******************************************************************************************************************/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
