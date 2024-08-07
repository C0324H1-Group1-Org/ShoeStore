package org.example.casestudymodule4shoestore.repositories.category;

import org.example.casestudymodule4shoestore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(products.id) AS totalProduct FROM products JOIN categories c ON products.cat_id = c.id WHERE c.id = :categoryId")
    int findTotalProductInCategory(@Param("categoryId") int categoryId);
}
