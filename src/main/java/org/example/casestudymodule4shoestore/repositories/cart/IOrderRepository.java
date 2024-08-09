package org.example.casestudymodule4shoestore.repositories.cart;

import org.example.casestudymodule4shoestore.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findTop5ByOrderByIdDesc();
}
