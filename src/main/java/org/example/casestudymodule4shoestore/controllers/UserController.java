package org.example.casestudymodule4shoestore.controllers;

import org.example.casestudymodule4shoestore.models.Category;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.category.ICategoryService;
import org.example.casestudymodule4shoestore.services.products.IProductService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

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
    public String shop(Model model,@RequestParam(name="pageNo",defaultValue = "1")Integer pageNo) {
        model.addAttribute("navbar", "shop");
        Page<Product> products = productService.getAll(pageNo);
        model.addAttribute("products", products);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
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
//            Iterable<Product> productInCategory = productService.findProductByCategory(product1.getCat().getId());
//            model.addAttribute("productInCategory", productInCategory);
        } else {
            return "error/404";
        }
        return "detail";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "name", required = false) String name, Model model,@RequestParam(name="pageNo",defaultValue = "1")Integer pageNo) {
        model.addAttribute("navbar", "shop");
        Page<Product> products = productService.findProductByName(name,pageNo);
        model.addAttribute("products", products);
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "/shop";
    }

    @GetMapping("/category{id}")
    public String shopCategory(@PathVariable Integer id, Model model,@RequestParam(name="pageNo",defaultValue = "1")Integer pageNo){
        model.addAttribute("navbar", "shop");
        Page<Product> products = productService.findProductByCategory(id,pageNo);
        model.addAttribute("products", products);
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "/shop";
    }

}
