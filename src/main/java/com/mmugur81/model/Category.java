package com.mmugur81.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Mugurel on 13.08.2016.
 * Category Model
 */

@Entity(name = "categories")
public class Category extends BaseModel {

    @Size(min=3, max=30)
    @Column(length = 30)
    private String name;

    @Lob
    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_category_parent", foreignKey = @ForeignKey(name = "fk_categories_category_id"))
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private List<Category> subCategories;

    /******************************************************************************************************************/

    public Category() { }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Category parentCategory) {
        this(name);
        this.parentCategory = parentCategory;
    }

    /******************************************************************************************************************/

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

    public Category getParentCategory() {
        return parentCategory;
    }

    public String getParentCategoryName() {
        return (this.parentCategory != null)? this.parentCategory.getName() : null;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }
}
