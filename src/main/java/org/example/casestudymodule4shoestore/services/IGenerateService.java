package org.example.casestudymodule4shoestore.services;

import org.example.casestudymodule4shoestore.models.Product;

import java.util.List;
import java.util.Optional;

public interface IGenerateService<T> {

    List<T> findAll();

    Optional<Product> findProductById(Long id);

    List<Product> sortProductsByPrice();


}
