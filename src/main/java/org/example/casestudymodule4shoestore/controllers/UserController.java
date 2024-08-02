package org.example.casestudymodule4shoestore.controllers;

import org.example.casestudymodule4shoestore.dtos.productDTO.ProductDTO;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.models.login.AppUser;
import org.example.casestudymodule4shoestore.services.product.IProductService;
import org.example.casestudymodule4shoestore.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public String home(){
        return "index";
    }

    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }

    @GetMapping("/detail")
    public String detail(){
        return "detail";
    }

    @GetMapping("/cart")
    public String cart(){
        return "cart";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/checkout")
    public String checkout(){
        return "checkout";
    }

    @GetMapping("/thankyou")
    public String thankYou(){
        return "thankyou";
    }

    @GetMapping("/detail/{id}")
    public String showProductDetail(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.findProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product);
        } else {
            return "error/404";
        }
        return "detail";
    }


}
