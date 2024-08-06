package org.example.casestudymodule4shoestore.dtos.product;


import lombok.Getter;
import lombok.Setter;
import org.example.casestudymodule4shoestore.models.Category;

@Setter
@Getter
public class ProductCategoryDTO {
    private Long id;
    private String name;
    private String image;
    private Float price;
    private Long categoryId;
    private String categoryName;


    public ProductCategoryDTO() {}
}
