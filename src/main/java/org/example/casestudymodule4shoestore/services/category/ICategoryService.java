package org.example.casestudymodule4shoestore.services.category;

import org.example.casestudymodule4shoestore.models.Category;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.IGenerateService;

import java.util.List;
import java.util.Optional;

public interface ICategoryService extends IGenerateService<Category> {
    int findTotalProductInCategory(Integer id);
}
