package org.example.casestudymodule4shoestore.services.products;

import org.example.casestudymodule4shoestore.dtos.product.CartDTO;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.repositories.products.IProductRepository;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService implements IGenerateService<Product> {
    List<CartDTO> cartDTOS = new ArrayList<>();





    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }
}
