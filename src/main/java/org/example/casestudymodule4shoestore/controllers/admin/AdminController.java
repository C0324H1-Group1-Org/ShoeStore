package org.example.casestudymodule4shoestore.controllers.admin;

import org.example.casestudymodule4shoestore.models.*;
import org.example.casestudymodule4shoestore.repositories.products.IProductSizeRepository;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.example.casestudymodule4shoestore.services.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;
    @Autowired
    IProductSizeRepository productSizeRepository;

    @GetMapping
    public String index(){
        return "admin/index";
    }
    @GetMapping("/products")
    public String products(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products",products);
        return "admin/products";
    }

    @GetMapping("/create")
    public String addNewProduct(Model model){
        List<Size> sizes = productService.findAllSize();
        List<Category> categories = productService.findAllCategory();
        List<Brand> brands = productService.findAllBrand();
        Product product = new Product();
        List<ProductSize> productSizes = new ArrayList<>();
        for (Size size : sizes){
            ProductSize productSize = new ProductSize();
            productSize.setIdSize(size);
            productSizes.add(productSize);
        }
        product.setProductSizes(productSizes);
        model.addAttribute("product",product);
        model.addAttribute("categories",categories);
        model.addAttribute("brands",brands);
        return "admin/add_new_product";
    }
    @PostMapping("/create")
    public String addNewProductPost(Product product){

        return "redirect:/admin/products";
    }

    @GetMapping("/test")
    public String test(){
        Product product = new Product();
        List<ProductSize> productSizes = new ArrayList<>();
        List<Size> sizes = productService.findAllSize();

        Category category = new Category();
        category.setId(1);
        category.setName("Nike");
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("Adidas");
        product.setName("New Test Product");
        product.setPrice(99888F);
        product.setDescription("Mo ta new test product");
        product.setCat(category);
        product.setBrand(brand);
        product = productService.save(product);
        for (Size size : sizes){
            ProductSize productSize = new ProductSize();
            ProductSizeId productSizeId = new ProductSizeId();
            productSizeId.setIdProduct(product.getId());
            productSizeId.setIdSize(size.getId());
            productSize.setId(productSizeId);
            productSize.setIdProduct(product);
            productSize.setIdSize(size);
            productSizes.add(productSize);
        }

        productSizeRepository.saveAll(productSizes);

        return "admin/products";
    }


}
