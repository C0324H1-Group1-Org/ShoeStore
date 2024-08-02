package org.example.casestudymodule4shoestore.services.product.impl;

import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.repositories.login.IProductRepository;
import org.example.casestudymodule4shoestore.services.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }
}
