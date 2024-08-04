package org.example.casestudymodule4shoestore.services.product;

import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.models.Size;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Optional<Product> findProductById(Long id);

    List<Size> findAllSize();
}
