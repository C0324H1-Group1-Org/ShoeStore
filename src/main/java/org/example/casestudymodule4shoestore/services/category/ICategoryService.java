package org.example.casestudymodule4shoestore.services.category;

import org.example.casestudymodule4shoestore.models.Category;
import org.example.casestudymodule4shoestore.services.IGenerateService;

import java.util.List;

public interface ICategoryService extends IGenerateService<Category> {
    List<Integer> findTotalProductInCategory(Integer id);
}
