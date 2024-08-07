package org.example.casestudymodule4shoestore.services.products;

import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IProductService extends IGenerateService<Product> {

    List<Product> sortProductsByPrice();

    List<Product> findProductByName(String keyword);

    Iterable<Product> findProductByCategory(Long id);

    Page<Product> findAll(Pageable pageable);
}
