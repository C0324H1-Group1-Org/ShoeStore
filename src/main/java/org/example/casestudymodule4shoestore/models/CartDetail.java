package org.example.casestudymodule4shoestore.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_detail")
public class CartDetail {
    @EmbeddedId
    private CartDetailId cartDetailId;

    @MapsId("idCart")
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart idCart;


    @MapsId("idProduct")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product idProduct;

    private int size;
    private int quantity;

}
