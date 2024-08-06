package org.example.casestudymodule4shoestore.services.products;

import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.IGenerateService;

import java.util.List;

public interface IProductService extends IGenerateService<Product> {


    List<Product> sortProductsByPrice();

    List<Product> findProductByName(String keyword);

    Iterable<Product> findProductByCategory(Integer id);
}
