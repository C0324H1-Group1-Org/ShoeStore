package org.example.casestudymodule4shoestore.services.category;

import org.example.casestudymodule4shoestore.models.Category;
import org.example.casestudymodule4shoestore.repositories.category.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private ICategoryRepo categoryRepo;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public List<Map<String, Object>> countProductsByCategory() {
        return categoryRepo.countProductsByCategory();
    }
}
