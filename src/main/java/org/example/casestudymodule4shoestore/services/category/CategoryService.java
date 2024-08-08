package org.example.casestudymodule4shoestore.services.category;

import org.example.casestudymodule4shoestore.models.Category;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.repositories.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return Optional.empty();
    }


    @Override
    public List<Integer> findTotalProductInCategory(Integer id) {
        return categoryRepository.findTotalProductInCategory(id);
    }
}
