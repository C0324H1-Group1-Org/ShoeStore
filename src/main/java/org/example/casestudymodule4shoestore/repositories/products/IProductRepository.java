package org.example.casestudymodule4shoestore.repositories.products;

import org.example.casestudymodule4shoestore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {

    @Query( nativeQuery = true, value = "select p.*  from products as p order by p.price desc")
    List<Product> sortProductsByPrice();

//    @Query( nativeQuery = true, value = "select p.id as id, p.name as name, p.image as image, p.price as price, c.id as categoryId, c.name as categoryName from products p join categories c on p.cat_id = c.id where c.id = :id")
//    List<ProductCategoryDTO> findByCategory(@Param("id") Long id);

    List<Product> findAllByCat_Id(Integer id);

    @Query( nativeQuery = true, value = "select p.*  from products as p where p.name like :keyword")
    List<Product> findAllByNameContaining(@Param("keyword")String keyword);

}
