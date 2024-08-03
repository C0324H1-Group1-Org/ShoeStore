package org.example.casestudymodule4shoestore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProductSizeId implements Serializable {

    @NotNull
    @Column(name = "id_product", nullable = false)
    private Integer idProduct;

    @NotNull
    @Column(name = "id_size", nullable = false)
    private Integer idSize;



}