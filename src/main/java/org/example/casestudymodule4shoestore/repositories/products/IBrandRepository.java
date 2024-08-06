package org.example.casestudymodule4shoestore.repositories.products;

import org.example.casestudymodule4shoestore.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<Brand,Integer> {
}
