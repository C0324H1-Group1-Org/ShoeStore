package org.example.casestudymodule4shoestore.repositories.products;

import org.example.casestudymodule4shoestore.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISizeRepository extends JpaRepository<Size,Integer> {
}
