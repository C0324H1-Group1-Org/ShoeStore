package org.example.casestudymodule4shoestore.services.products;

import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.repositories.products.IProductRepository;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService implements IGenerateService<Product>{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<Product> findAllPagi(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }
}
