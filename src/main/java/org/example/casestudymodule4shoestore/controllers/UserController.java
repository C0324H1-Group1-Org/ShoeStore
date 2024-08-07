package org.example.casestudymodule4shoestore.controllers;

import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private IGenerateService productService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("navbar", "index");
        return "index";
    }

    @GetMapping("/shop")
    public String shop(@RequestParam(value = "page", required = false, defaultValue = "1")int page, Model model) {
        Pageable pageable = PageRequest.of(page-1, 5);
        Page<Product> products = productService.findAllPagi(pageable);
        List<Product> productList = products.getContent();
        model.addAttribute("products", productList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        return "shop";
    }



    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("navbar", "about");
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("navbar", "contact");
        return "contact";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/thankyou")
    public String thankYou() {
        return "thankyou";
    }

    @GetMapping("/detail/{id}")
    public String showProductDetail(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.findProductById(id);

        if (product.isPresent()) {
            Product product1 = product.get();
            model.addAttribute("product", product1);
        } else {
            return "error/404";
        }
        return "detail";
    }

}
