package org.example.casestudymodule4shoestore.repositories.products;

import org.example.casestudymodule4shoestore.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IProductRepository extends JpaRepository<Product,Long> {
}
