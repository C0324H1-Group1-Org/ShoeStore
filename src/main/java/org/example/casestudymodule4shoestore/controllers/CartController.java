package org.example.casestudymodule4shoestore.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.casestudymodule4shoestore.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class CartController {

    @PostMapping("/cart")
    public String addCart(@ModelAttribute("product") Product product,
                          @RequestParam("size") int size,
                          @RequestParam("quantity") int quantity,
                          HttpServletResponse response,
                          RedirectAttributes redirectAttributes) {
        try {
            String encodedName = URLEncoder.encode(product.getName(), "UTF-8");
            String encodedPrice = URLEncoder.encode(String.valueOf(product.getPrice()), "UTF-8");
            String encodedImage = URLEncoder.encode(product.getImage(), "UTF-8");
            String encodedSize = URLEncoder.encode(String.valueOf(size), "UTF-8");
            String encodedQuantity = URLEncoder.encode(String.valueOf(quantity), "UTF-8");


            Cookie cookieName = new Cookie("NameProduct", encodedName);
            cookieName.setHttpOnly(true);
            cookieName.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(cookieName);

            Cookie cookiePrice = new Cookie("PriceProduct", encodedPrice);
            cookiePrice.setHttpOnly(true);
            cookiePrice.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(cookiePrice);

            Cookie cookieQuantity = new Cookie("QuantityProduct", encodedQuantity);
            cookieQuantity.setHttpOnly(true);
            cookieQuantity.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(cookieQuantity);

            Cookie cookieImage = new Cookie("ImageProduct", encodedImage);
            cookieImage.setHttpOnly(true);
            cookieImage.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(cookieImage);

            Cookie cookieSize = new Cookie("SizeProduct", encodedSize);
            cookieSize.setHttpOnly(true);
            cookieSize.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(cookieSize);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "redirect:/detail/{id}" + product.getId();
    }
}
