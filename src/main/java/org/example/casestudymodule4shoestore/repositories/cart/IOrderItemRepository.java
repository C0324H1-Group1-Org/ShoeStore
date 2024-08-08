package org.example.casestudymodule4shoestore.repositories.cart;

import org.example.casestudymodule4shoestore.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
