package com.mmugur81.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by mugurel on 13.09.2016.
 * Order details (products bought)
 */

@Entity
@Table(
    name = "order_items",
    uniqueConstraints = @UniqueConstraint(
        name = "uq_oi_order_item_no",
        columnNames = {"id_order", "itemNumber"}
    )
)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", foreignKey = @ForeignKey(name = "fk_oi_orders_id"))
    private Order order;

    @NotNull
    private short itemNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", foreignKey = @ForeignKey(name = "fk_oi_products_id"))
    private Product product;

    @NotNull
    @Embedded
    private Price price;

    /******************************************************************************************************************/

    public OrderItem() {
    }

    public OrderItem(Order order, short itemNumber, Product product) {
        this.order = order;
        this.itemNumber = itemNumber;
        this.product = product;
        this.price = product.getPrice();
    }

    /******************************************************************************************************************/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public short getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(short itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
