package org.example.casestudymodule4shoestore.repositories.cart;

import jakarta.transaction.Transactional;
import org.example.casestudymodule4shoestore.models.Cart;
import org.example.casestudymodule4shoestore.models.CartDetail;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ICartDetailRepository extends JpaRepository<CartDetail,Integer> {


    CartDetail findCartDetailByIdCartAndIdProductAndIdSize(Cart cart, Product product, Size size);

    @Modifying
    @Transactional
    @Query(value = "delete from cart_detail c where c.cart_id = ?1 and c.product_id = ?2 and c.size_id = ?3",nativeQuery = true)
    void deleteCartItem(Long idCart,Long idProduct,Long idSize);
}
