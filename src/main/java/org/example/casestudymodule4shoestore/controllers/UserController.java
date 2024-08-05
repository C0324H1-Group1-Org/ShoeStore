package org.example.casestudymodule4shoestore.controllers;

import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String home(Model model){
        model.addAttribute("navbar", "index");
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("products", productService.findAll());
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
    public String about(Model model){
        model.addAttribute("navbar", "about");

        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model ){
        model.addAttribute("navbar", "contact");
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

    @PostMapping("/search")
    public String  search(Model model){
        model.addAttribute("navbar", "shop");
//        List<Product> products = productService.findAll();
//        return new ResponseEntity<>(products, HttpStatus.OK);
        return "shop";
    }

}
