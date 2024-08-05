package org.example.casestudymodule4shoestore.controllers.admin;

import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.example.casestudymodule4shoestore.services.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
