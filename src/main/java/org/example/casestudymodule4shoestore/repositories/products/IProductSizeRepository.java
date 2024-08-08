package org.example.casestudymodule4shoestore.repositories.products;

import org.example.casestudymodule4shoestore.models.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductSizeRepository extends JpaRepository<ProductSize,Integer> {

}
