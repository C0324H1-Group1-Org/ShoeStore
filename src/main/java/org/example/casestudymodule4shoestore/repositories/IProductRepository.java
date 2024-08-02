package org.example.casestudymodule4shoestore.repository;

import org.example.casestudymodule4shoestore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {

}
