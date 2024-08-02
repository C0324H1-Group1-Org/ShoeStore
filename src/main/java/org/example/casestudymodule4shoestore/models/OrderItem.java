package org.example.casestudymodule4shoestore.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_item", schema = "module4_casestudy_shoe_store")
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @Column(name = "quantity")
    private Integer quantity;

}