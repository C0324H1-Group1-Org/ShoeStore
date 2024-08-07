package org.example.casestudymodule4shoestore.services.category;

import org.example.casestudymodule4shoestore.models.Category;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
    Iterable<Category> findAll();
    List<Map<String, Object>> countProductsByCategory();
}
