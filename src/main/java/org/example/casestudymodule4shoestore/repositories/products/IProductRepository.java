package org.example.casestudymodule4shoestore.repositories.products;

import jakarta.transaction.Transactional;
import org.example.casestudymodule4shoestore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends JpaRepository<Product, Long> {
    // Đếm số lượng product_id
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM cart_detail WHERE product_id = :productId")
    int countProductInCart(@Param("productId") Integer productId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE cart_detail SET quantity = :quantity, size = :size WHERE product_id = :productId")
    void updateCartDetailByIdProduct(@Param("quantity") Integer quantity, @Param("size") Integer size, @Param("productId") Integer productId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO cart_detail (product_id, quantity, size, cart_id) VALUES (:productId, :quantity, :size, :idCart)")
    void insertProductToCart(@Param("productId") Integer productId, @Param("quantity") int quantity, @Param("size") int size, @Param("idCart") int idCart);

    default boolean addProduct(Integer productId, int quantity, int size, int idCart) {
        int count = countProductInCart(productId);
        if (count == 0) {
            insertProductToCart(productId, quantity, size, idCart);
            return true;
        } else if (count > 0) {
            updateCartDetailByIdProduct(quantity, size, productId);
            return true;
        }
        return false;
    }

    @Query(nativeQuery = true, value = "SELECT cart_id FROM cart WHERE customer_id = :idCustomer")
    int findIdCart(@Param("idCustomer") int idCustomer);
}
