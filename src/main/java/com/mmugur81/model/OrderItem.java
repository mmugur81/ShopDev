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
}
