package org.example.casestudymodule4shoestore.dtos.product;

import lombok.Getter;
import lombok.Setter;
import org.example.casestudymodule4shoestore.models.Size;
@Getter
@Setter
public class CartDTO {
    private String nameProduct;
    private String image;
    private Size size;
    private int quantity;
    private Float price;
    private String nameCustomer;
    private String email;
    private String phone;

    public CartDTO() {
    }
}
