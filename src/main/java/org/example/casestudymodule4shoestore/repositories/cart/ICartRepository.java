package org.example.casestudymodule4shoestore.repositories.cart;

import org.example.casestudymodule4shoestore.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository <Cart,Integer> {
    Cart findByCustomerId (Integer customerId);
}
