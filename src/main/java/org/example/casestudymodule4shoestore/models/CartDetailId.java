package org.example.casestudymodule4shoestore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class CartDetailId implements Serializable {
    @NotNull
    @Column(name = "id_cart", nullable = false)
    private Integer idCart;
    @NotNull
    @Column(name = "id_product", nullable = false)
    private Integer idProduct;
    @NotNull
    @Column(name = "id_size",nullable = false)
    private Integer idSize;
}
