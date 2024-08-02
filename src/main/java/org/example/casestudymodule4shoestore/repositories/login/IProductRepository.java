package org.example.casestudymodule4shoestore.repositories.login;

import org.example.casestudymodule4shoestore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
}
