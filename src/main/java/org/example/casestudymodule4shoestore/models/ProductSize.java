package org.example.casestudymodule4shoestore.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_size")
public class ProductSize {
    @EmbeddedId
    private ProductSizeId id;

    @MapsId("idProduct")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product idProduct;

    @MapsId("idSize")
    @ManyToOne
    @JoinColumn(name = "id_size",nullable = false)
    private Size idSize;

    @Column(name = "quantity")
    private Integer quantity;

}