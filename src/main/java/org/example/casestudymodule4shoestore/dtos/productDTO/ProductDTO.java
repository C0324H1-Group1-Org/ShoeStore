package org.example.casestudymodule4shoestore.dtos.productDTO;

import lombok.Getter;
import lombok.Setter;
import org.example.casestudymodule4shoestore.models.Size;


@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Float price;
    private Size size;
    public ProductDTO() {
    }

}
