package org.example.casestudymodule4shoestore.controllers;

import org.example.casestudymodule4shoestore.models.Cart;
import org.example.casestudymodule4shoestore.models.CartDetail;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.example.casestudymodule4shoestore.services.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private  CartService cartService;
//    @PostMapping("/addCart")
//    public String addCart(@ModelAttribute("product") Product product,
//                          @RequestParam("quantity") int quantity,
//                          RedirectAttributes redirectAttributes) {
//        if (product.getId().describeConstable().isPresent()) {
//
//        } else {
//            redirectAttributes.addFlashAttribute("product", product);
//            redirectAttributes.addFlashAttribute("quantity", quantity);
//            redirectAttributes.addFlashAttribute("message", "Thêm vào giỏ hàng thành công");
//        }
//
//        return "redirect:/detail/{id}" + product.getId();
//    }
    @GetMapping("/cart")
    public String cart(Model model  ) {
        Cart cart = cartService.findCartByCustomerId(1);
        float totalPrice = 0;
        for (CartDetail cartDetail: cart.getCartDetailList()){
            totalPrice += cartDetail.getQuantity() * cartDetail.getIdProduct().getPrice();
        }
        model.addAttribute("cart" , cart);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
}
    @GetMapping("/cart/remove")
    public String removeProductFromCart(@RequestParam("id") Long productId, Model model) {
        Cart cart = cartService.findCartByCustomerId(1);
        cartService.removeProductFromCart(cart, productId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String updateProductQuantity(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity, Model model) {
        Cart cart = cartService.findCartByCustomerId(1);
        cartService.updateProductQuantity(cart, productId, quantity);
        return "redirect:/cart";
    }
}
