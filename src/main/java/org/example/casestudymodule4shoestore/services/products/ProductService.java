package org.example.casestudymodule4shoestore.services.products;

import org.example.casestudymodule4shoestore.models.Brand;
import org.example.casestudymodule4shoestore.models.Category;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.models.Size;
import org.example.casestudymodule4shoestore.repositories.products.IBrandRepository;
import org.example.casestudymodule4shoestore.repositories.products.ICategoryRepository;
import org.example.casestudymodule4shoestore.repositories.products.IProductRepository;
import org.example.casestudymodule4shoestore.repositories.products.ISizeRepository;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService implements IGenerateService<Product> {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ISizeRepository sizeRepository;
    @Autowired
    private IBrandRepository brandRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public List<Size> findAllSize(){
        return sizeRepository.findAll();
    }
    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }
    public List<Brand> findAllBrand(){
        return brandRepository.findAll();
    }
}
