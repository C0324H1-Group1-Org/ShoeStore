package org.example.casestudymodule4shoestore.repositories.products;

import org.example.casestudymodule4shoestore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {

    @Query( nativeQuery = true, value = "select p.*  from products as p order by p.price desc")
    List<Product> sortProductsByPrice();
}
