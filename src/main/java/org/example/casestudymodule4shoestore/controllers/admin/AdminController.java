package org.example.casestudymodule4shoestore.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String index(){
        return "admin/index";
    }
    @GetMapping("/products")
    public String producsts(){
        return "admin/products";
    }
}
