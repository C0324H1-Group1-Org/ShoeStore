package org.example.casestudymodule4shoestore.services.products;

import org.example.casestudymodule4shoestore.dtos.product.CartDTO;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.repositories.products.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

   @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public boolean addProduct(Integer productId, int quantity, int size,int idCart) {
        return productRepository.addProduct(productId,quantity,size,idCart);
    }

    public int findIdCart(int idCustomer) {
        return productRepository.findIdCart(idCustomer);
    }
      
    public List<Product> sortProductsByPrice() {
        return productRepository.sortProductsByPrice();
    }

    @Override
    public Iterable<Product> findProductByCategory(Long id) {
        return productRepository.findAllByCat_Id(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findAllByNameContaining("%" + name + "%");
    }
}
