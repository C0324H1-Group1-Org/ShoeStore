package org.example.casestudymodule4shoestore.repositories.category;

import org.example.casestudymodule4shoestore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ICategoryRepo extends JpaRepository<Category,Long> {
    @Query(nativeQuery = true, value = "SELECT c.id as c_id,c.name as c_name, COUNT(*) AS product_count FROM products p join categories c on c.id = p.cat_id GROUP BY cat_id")
    List<Map<String, Object>> countProductsByCategory();
}
