package org.example.casestudymodule4shoestore.repositories.cart;

import org.example.casestudymodule4shoestore.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface ICartRepository extends JpaRepository <Cart,Integer> {
    Cart findByCustomerId (Integer customerId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart_detail WHERE cart_id = :cartId AND product_id = :productId", nativeQuery = true)
    void removeProductFromCart(@Param("cartId") Long cartId, @Param("productId") Long productId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cart_detail SET quantity = :quantity WHERE cart_id = :cartId AND product_id = :productId", nativeQuery = true)
    void updateProductQuantity(@Param("cartId") Long cartId, @Param("productId") Long productId, @Param("quantity") int quantity);
}
