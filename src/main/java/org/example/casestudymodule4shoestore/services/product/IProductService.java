package org.example.casestudymodule4shoestore.services.product;

import org.example.casestudymodule4shoestore.models.Product;

import java.util.Optional;

public interface IProductService {
    Optional<Product> findProductById(Long id);

}
