package org.example.casestudymodule4shoestore.controllers;

import org.example.casestudymodule4shoestore.models.Category;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.category.ICategoryService;
import org.example.casestudymodule4shoestore.services.products.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    private Iterable<Category> showAllCategory(Model model) {
        Iterable<Category> categories = categoryService.findAll();
        List<Map<String, Object>> categoryProductCounts = categoryService.countProductsByCategory();
        model.addAttribute("categoryProductCounts", categoryProductCounts);
        return categories;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("navbar", "index");
        List<Product> products = productService.sortProductsByPrice();
        List<Product> pp  = new ArrayList<>();
        pp.add(products.get(0));
        pp.add(products.get(1));
        pp.add(products.get(2));
        pp.add(products.get(3));
        pp.add(products.get(4));
        pp.add(products.get(5));
        model.addAttribute("products", pp );
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model,
                       @RequestParam(defaultValue = "0") int page) {
        Page<Product> products = productService.findAll(PageRequest.of(page, 6));
        model.addAttribute("products_page", products);
        model.addAttribute("paginationBaseUrl", "/shop");
        model.addAttribute("paginationPreviousUrl", products.hasPrevious() ? "/shop?page=" + (products.getNumber() - 1) : "#");
        model.addAttribute("paginationNextUrl", products.hasNext() ? "/shop?page=" + (products.getNumber() + 1) : "#");
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

    @GetMapping("/search")
    public String search(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("navbar", "search");
        List<Product> products = productService.findProductByName(name);
        model.addAttribute("products", products);
        return "/shop";
    }

    @GetMapping("/category{id}")
    public String shopCategory(@PathVariable Long id,
                               Model model,
                               @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("navbar", "category");
        Page<Product> products = productService.findProductByCategory(id, PageRequest.of(page, 6));
        model.addAttribute("products_page", products);
        model.addAttribute("paginationBaseUrl", "/category" + id);
        model.addAttribute("paginationPreviousUrl", products.hasPrevious() ? "/category" + id + "?page=" + (products.getNumber() - 1) : "#");
        model.addAttribute("paginationNextUrl", products.hasNext() ? "/category" + id + "?page=" + (products.getNumber() + 1) : "#");
        return "shop";
    }

}
