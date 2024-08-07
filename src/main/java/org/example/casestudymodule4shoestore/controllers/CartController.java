package org.example.casestudymodule4shoestore.controllers;

import org.example.casestudymodule4shoestore.dtos.login.UserInfoUserDetails;
import org.example.casestudymodule4shoestore.models.Cart;
import org.example.casestudymodule4shoestore.models.CartDetail;
import org.example.casestudymodule4shoestore.models.Customer;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.example.casestudymodule4shoestore.services.cart.CartService;
import org.example.casestudymodule4shoestore.services.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CartController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @PostMapping("/addCart")
    public String addCart(@RequestParam("id") Integer productId,
                          @RequestParam("quantity") int quantity,
                          @RequestParam("size") int size,
                          RedirectAttributes redirectAttributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        // Lấy thông tin CustomUserDetails
//        UserInfoUserDetails userDetails = (UserInfoUserDetails) authentication.getPrincipal();
//        Long idCustomer = userDetails.getIdCustomer();

        int idCustomer = 1;
        int idCart = productService.findIdCart(idCustomer);

        boolean check = productService.addProduct(productId, quantity, size,idCart);
        if (check) {
            redirectAttributes.addFlashAttribute("message", "Thêm vào giỏ hàng thành công");
        } else {
            redirectAttributes.addFlashAttribute("message", "Thêm vào giỏ hàng thất bại!");
        }
        return "redirect:/detail/" + productId;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        Cart cart = cartService.findCartByCustomerId(1);
        float totalPrice = 0;
        for (CartDetail cartDetail : cart.getCartDetailList()) {
            totalPrice += cartDetail.getQuantity() * cartDetail.getIdProduct().getPrice();
        }
        model.addAttribute("cart" , cart);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
}
}
