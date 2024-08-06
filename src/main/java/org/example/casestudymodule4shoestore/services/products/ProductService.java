package org.example.casestudymodule4shoestore.services.products;

import org.example.casestudymodule4shoestore.models.*;
import org.example.casestudymodule4shoestore.repositories.products.*;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.repositories.products.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ISizeRepository sizeRepository;
    @Autowired
    private IBrandRepository brandRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IProductSizeRepository productSizeRepository;

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
    public void saveAllProductSize(List<ProductSize> productSizes){
        productSizeRepository.saveAll(productSizes);
    }


    public List<Product> sortProductsByPrice(){
        return productRepository.sortProductsByPrice();
    }

    @Override
    public Iterable<Product> findProductByCategory(Integer id) {
        return productRepository.findAllByCat_Id(id);
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findAllByNameContaining("%" + name + "%");
    }
}
