package org.example.casestudymodule4shoestore.controllers.admin;

import org.example.casestudymodule4shoestore.models.*;
import org.example.casestudymodule4shoestore.services.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/create-product")
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
    @PostMapping("/create-product")
    public String addNewProductPost(Product product){
        Product savedProduct = productService.save(product);
        List<ProductSize> productSizes = product.getProductSizes();
        for (ProductSize productSize : productSizes){
            ProductSizeId productSizeId = new ProductSizeId();
            productSizeId.setIdProduct(savedProduct.getId());
            productSizeId.setIdSize(productSize.getIdSize().getId());
            productSize.setId(productSizeId);
            productSize.setIdSize(productSize.getIdSize());
            productSize.setIdProduct(savedProduct);
        }
        productService.saveAllProductSize(productSizes);
        return "redirect:/admin/products";
    }




}
