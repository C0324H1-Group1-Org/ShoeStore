package org.example.casestudymodule4shoestore.services.products;

import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.IGenerateService;

import java.util.List;

public class ProductService implements IGenerateService<Product> {
    @Override
    public List<Product> findAll() {
        return List.of();
    }
}
