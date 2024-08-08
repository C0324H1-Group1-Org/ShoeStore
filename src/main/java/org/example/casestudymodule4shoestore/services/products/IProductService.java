package org.example.casestudymodule4shoestore.services.products;

import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IGenerateService<Product> {


    List<Product> sortProductsByPrice();

    Page<Product> findProductByName(String keyword,Integer pageNo);

    Page<Product> findProductByCategory(Integer id,Integer pageNo);

    Page<Product> findPaginated(int pageNo,int pageSize);

    Page<Product> getAll(Integer pageNo);
}
