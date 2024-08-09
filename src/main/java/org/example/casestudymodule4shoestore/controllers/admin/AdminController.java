package org.example.casestudymodule4shoestore.controllers.admin;

import org.example.casestudymodule4shoestore.models.*;
import org.example.casestudymodule4shoestore.services.cart.OrderService;
import org.example.casestudymodule4shoestore.services.customer.CustomerService;
import org.example.casestudymodule4shoestore.services.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;


    @GetMapping
    public String index(Model model){
        long totalCustomer = customerService.countTotalCustomer();
        long totalOrder = orderService.countTotalOrder();
        List<Order> orders = orderService.findFiveLastOrder();
        List<Customer> customers = customerService.findFiveLastCustomer();
        model.addAttribute("totalCustomer",totalCustomer);
        model.addAttribute("totalOrder",totalOrder);
        model.addAttribute("orders",orders);
        model.addAttribute("customers",customers);
        return "admin/index";
    }
    @GetMapping("/products")
    public String products(Model model){
//        List<Product> products = productService.findAll();
//        model.addAttribute("products",products);

        return findPaginated(1,model);
    }
    @GetMapping("/products/page/{page}")
    public String findPaginated(@PathVariable("page") int pageNo , Model model){
        int pageSize = 5;
        Page<Product> pages = productService.findPaginated(pageNo,pageSize);
        List<Product> products = pages.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPage",pages.getTotalPages());
        model.addAttribute("totalItems",pages.getTotalElements());
        model.addAttribute("products",products);
        return "admin/products";
    }

    @GetMapping("/create-product")
    public String addNewProduct(Model model){
        List<Size> sizes = productService.findAllSize();
        List<Category> categories = productService.findAllCategory();
        List<Brand> brands = productService.findAllBrand();
        Product product = new Product();
        List<ProductSize> productSizes = new ArrayList<>();
        for (Size size : sizes){
            ProductSize productSize = new ProductSize();
            productSize.setIdSize(size);
            productSizes.add(productSize);
        }
        product.setProductSizes(productSizes);
        model.addAttribute("product",product);
        model.addAttribute("categories",categories);
        model.addAttribute("brands",brands);
        return "admin/add_new_product";
    }
    @PostMapping("/save-product")
    public String addNewProductPost(Product product){
        Product savedProduct = productService.save(product);
        List<ProductSize> productSizes = product.getProductSizes();
        for (ProductSize productSize : productSizes){
            ProductSizeId productSizeId = new ProductSizeId();
            productSizeId.setIdProduct(savedProduct.getId());
            productSizeId.setIdSize(productSize.getIdSize().getId());
            productSize.setId(productSizeId);
            productSize.setIdSize(productSize.getIdSize());
            productSize.setIdProduct(savedProduct);
        }
        productService.saveAllProductSize(productSizes);
        return "redirect:/admin/products";
    }

    @GetMapping("/update-product/{id}")
    public String updateProductById(@PathVariable("id") Long id, Model model){
        List<Size> sizes = productService.findAllSize();
        List<Category> categories = productService.findAllCategory();
        List<Brand> brands = productService.findAllBrand();
        Optional<Product> product = productService.findProductById(id);
        if (product.isPresent()){
            model.addAttribute("product",product.get());
        } else {
            return "error/404";
        }
        model.addAttribute("categories",categories);
        model.addAttribute("brands",brands);
        return "admin/add_new_product";
    }

    @PostMapping("/delete-product/{id}")
    public String deleteProductById(@PathVariable Long id){
        productService.remove(id);
        return "redirect:/admin/products";
    }
    @GetMapping("/orders")
    public String orders(Model model){
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders",orders);
        return "admin/orders";
    }

    @PostMapping("/update-orders/{id}")
    public String updateOrder(@PathVariable("id") Integer id, @RequestParam("orderUpdate") String status){
        Optional<Order> optional = orderService.findById(id);
        if (optional.isPresent()){
            Order order = optional.get();
            order.setStatus(StatusEnum.valueOf(status));
            orderService.saveOrder(order);
        }
        else {
            return "error/404";
        }
        return "redirect:/admin/orders";
    }

}
