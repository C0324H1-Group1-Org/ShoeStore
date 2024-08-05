package org.example.casestudymodule4shoestore.controllers;

import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CartController {
    @Autowired
    private IGenerateService productService;

    @PostMapping("/addCart")
    public String addCart(@ModelAttribute("product") Product product,
                          @RequestParam("quantity") int quantity,
                          RedirectAttributes redirectAttributes) {

        boolean check = productService.addProduct(product, quantity);
        if (check) {
            redirectAttributes.addFlashAttribute("message", "Thêm vào giỏ hàng thành công");
        }else {
            redirectAttributes.addFlashAttribute("message", "Thêm vào giỏ hàng thất bại!");
        }
        return "redirect:/detail/{id}" + product.getId();
    }
}
