package org.example.casestudymodule4shoestore.repositories.products;

import jakarta.transaction.Transactional;
import org.example.casestudymodule4shoestore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends JpaRepository<Product, Long> {

//    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM cart_detail WHERE product_id = :productId")
//    Long countProductInCart(@Param("productId") Long productId);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO cart_detail (product_id, quantity, size,cart_id) VALUES (:productId, :quantity, :size ,:idCart)")
    void insertProductToCart(@Param("productId") Integer productId, @Param("quantity") int quantity, @Param("size") int size,@Param("idCart") int idCart);

    default boolean addProduct(Product product, int quantity, int size,int idCart) {
        int productId = Integer.valueOf(product.getId());
//        if (countProductInCart(productId) == 0) {
            insertProductToCart(productId, quantity, size,idCart);
            return true;
//        }
//        if (countProductInCart(productId) > 0) {
//        }
//        return false;
    }

    @Query(nativeQuery = true,value = "select cart_id from cart where customer_id = :idCustomer")
    int findIdCart(int idCustomer);

}
