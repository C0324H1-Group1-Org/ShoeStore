package org.example.casestudymodule4shoestore.repositories.cart;

import org.example.casestudymodule4shoestore.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order,Integer> {

}
