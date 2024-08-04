package org.example.casestudymodule4shoestore.dtos.product;

import lombok.Getter;
import lombok.Setter;
import org.example.casestudymodule4shoestore.models.Brand;
import org.example.casestudymodule4shoestore.models.Category;



@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Float price;
    private Brand brand;
    private Category category;

    public ProductDTO() {
    }

}
