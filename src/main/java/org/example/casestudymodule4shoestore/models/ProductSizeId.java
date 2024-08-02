package org.example.casestudymodule4shoestore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProductSizeId implements java.io.Serializable {
    private static final long serialVersionUID = 6630444419808248666L;
    @NotNull
    @Column(name = "id_product", nullable = false)
    private Integer idProduct;

    @NotNull
    @Column(name = "id_size", nullable = false)
    private Integer idSize;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductSizeId entity = (ProductSizeId) o;
        return Objects.equals(this.idProduct, entity.idProduct) &&
                Objects.equals(this.idSize, entity.idSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, idSize);
    }

}