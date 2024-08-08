package org.example.casestudymodule4shoestore.controllers;

import org.example.casestudymodule4shoestore.dtos.login.UserInfoUserDetails;
import org.example.casestudymodule4shoestore.models.*;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.example.casestudymodule4shoestore.services.cart.CartService;
import org.example.casestudymodule4shoestore.services.cart.OrderService;
import org.example.casestudymodule4shoestore.services.customer.CustomerService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @PostMapping("/addCart")
    public String addCart(@RequestParam("id") Long productId,
                          @RequestParam("quantity") int quantity,
                          @RequestParam("size") int size,
                          RedirectAttributes redirectAttributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
//         Lấy thông tin CustomUserDetails
        String userName = authentication.getName();
        Customer customer = customerService.findCustomerByUserName(userName);
        Cart cart = cartService.findCartByCustomerId(customer.getId());
        if (cart == null){
            cart = new Cart();
            cart.setCustomer(customer);
            cart = cartService.save(cart);
        }
        Optional<Product> optional = productService.findProductById(productId);
        Optional<Size> optionalSize = productService.findSizeById(size);

        if (optional.isPresent()){
            Product product = optional.get();
            for (ProductSize productSize : product.getProductSizes()){
                if (productSize.getIdSize().getNumber().equals(optionalSize.get().getNumber())){
                    if (productSize.getQuantity() < quantity){
                        redirectAttributes.addFlashAttribute("message","Số lượng vượt quá sản phẩm có sẵn");
                        return "redirect:/detail/" + productId;
                    }
                    else {
                        CartDetail cartDetail = productService.existByProductWhereCartId(product,cart,optionalSize.get());
                        if (cartDetail != null){
                            if (cartDetail.getQuantity()+quantity > productSize.getQuantity()){
                                redirectAttributes.addFlashAttribute("message", "Số lượng trong giỏ hàng vượt quá sản phẩm có sẵn");
                            }
                            else{
                                cartDetail.setQuantity(quantity + cartDetail.getQuantity());
                                productService.saveCartDetail(cartDetail);
                                redirectAttributes.addFlashAttribute("message", "Thêm vào giỏ hàng thành công");
                            }
                            return "redirect:/detail/" + productId;
                        }
                        else {
                            CartDetailId cartDetailId = new CartDetailId();
                            cartDetailId.setIdCart(cart.getCart_id());
                            cartDetailId.setIdProduct(product.getId());
                            cartDetailId.setIdSize(optionalSize.get().getId());
                            CartDetail cartDetailNew = new CartDetail();
                            cartDetailNew.setIdCart(cart);
                            cartDetailNew.setIdProduct(product);
                            cartDetailNew.setIdSize(optionalSize.get());
                            cartDetailNew.setCartDetailId(cartDetailId);
                            cartDetailNew.setQuantity(quantity);
                            productService.saveCartDetail(cartDetailNew);
                        }
                    }
                }
            }

        }else {
            return "error/404";
        }


//        int idCustomer = 1;
//        int idCart = productService.findIdCart(idCustomer);
//
//        boolean check = productService.addProduct(productId, quantity, size,idCart);
//        if (check) {
//            redirectAttributes.addFlashAttribute("message", "Thêm vào giỏ hàng thành công");
//        } else {
//            redirectAttributes.addFlashAttribute("message", "Thêm vào giỏ hàng thất bại!");
//        }
        return "redirect:/detail/" + productId;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        String userName = authentication.getName();
        Customer customer = customerService.findCustomerByUserName(userName);
        Cart cart = cartService.findCartByCustomerId(customer.getId());
        if (cart == null || cart.getCartDetailList() == null || cart.getCartDetailList().isEmpty()) {
            model.addAttribute("emptyCartMessage", "Giỏ hàng của bạn hiện chưa có sản phẩm.");
            return "cart";
        }

        float totalPrice = 0;
        for (CartDetail cartDetail : cart.getCartDetailList()) {
            totalPrice += cartDetail.getQuantity() * cartDetail.getIdProduct().getPrice();
        }
        model.addAttribute("cart" , cart);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @PostMapping("/update-cart")
    public String updateCart(@ModelAttribute Cart cart){
        Cart save = cartService.save(cart);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        String userName = authentication.getName();
        Customer customer = customerService.findCustomerByUserName(userName);
        Cart cart = cartService.findCartByCustomerId(customer.getId());
        if (cart == null) {
            return "redirect:/";
        }
        Order order = new Order();
        order.setEmail(customer.getEmail());
        order.setFirstName(customer.getFirstName());
        order.setLastName(customer.getLastName());
        order.setPhone(customer.getPhone());
        float totalPrice = 0;
        if (cart.getCartDetailList() != null){
            for (CartDetail cartDetail : cart.getCartDetailList()) {
                totalPrice += cartDetail.getQuantity() * cartDetail.getIdProduct().getPrice();
            }
        }
        model.addAttribute("order",order);
        model.addAttribute("cart",cart);
        model.addAttribute("totalPrice",totalPrice);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkoutPost(Order order){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        String userName = authentication.getName();
        Customer customer = customerService.findCustomerByUserName(userName);
        Cart cart = cartService.findCartByCustomerId(customer.getId());

        order.setCustomer(customer);
        order.setStatus(StatusEnum.PENDING);
        order.setOrderDate(new Date());
        order = orderService.saveOrder(order);
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartDetail cartDetail : cart.getCartDetailList()){
            OrderItemId orderItemId = new OrderItemId();
            orderItemId.setOrderId(order.getId());
            orderItemId.setProductId(cartDetail.getIdProduct().getId());
            orderItemId.setSizeId(cartDetail.getIdSize().getId());
            OrderItem orderItem = new OrderItem();
            orderItem.setId(orderItemId);
            orderItem.setOrder(order);
            orderItem.setSize(cartDetail.getIdSize());
            orderItem.setProduct(cartDetail.getIdProduct());
            orderItem.setQuantity(cartDetail.getQuantity());
            orderItemList.add(orderItem);
        }
        orderService.saveOrderItem(orderItemList);
        cartService.remove(cart);
        return "/thankyou";
    }

    @PostMapping("/delete-cart-item")
    public String deleteCartItem(@RequestParam("idProduct")Long idProduct,@RequestParam("idCart") Long idCart,@RequestParam("idSize") Long idSize){
        cartService.deleteCartItem(idCart,idProduct,idSize);
        return "redirect:/cart";
    }



}
