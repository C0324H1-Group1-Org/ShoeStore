package org.example.casestudymodule4shoestore.controllers.customer;

import org.example.casestudymodule4shoestore.dtos.customer.CustomerDto;
import org.example.casestudymodule4shoestore.models.AppUser;
import org.example.casestudymodule4shoestore.models.Customer;
import org.example.casestudymodule4shoestore.services.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/register")
    public String showFormRegister(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "login-register/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute CustomerDto customerDto,
                           BindingResult result,
                           Model model,
                           @RequestParam String confirmPassword) {
        model.addAttribute("isSubmitted", true);
        if (result.hasFieldErrors()) {
            model.addAttribute("customerDto", customerDto);
            return "login-register/register";
        }
//      Check tài khoản khi đăng ký
        if (customerService.emailExists(customerDto.getEmail())) {
            model.addAttribute("customerDto", customerDto);
            model.addAttribute("registrationError", "Tên đăng nhập đã tồn tại");
            return "login-register/register";
        }
//      check password có giống nhau k
        if(!confirmPassword.equals(customerDto.getPassword())) {
            model.addAttribute("customerDto", customerDto);
            model.addAttribute("checkPassword", "Mật khẩu không khớp!");
            return "login-register/register";
        }
//      set cho tk hoạt động
        customerDto.setEnabled(true);
//      mã hoá password
        String encodedPassword = passwordEncoder.encode(customerDto.getPassword());
//      lưu vào tk
        AppUser appUser = new AppUser(customerDto.getId(),customerDto.getEmail(),encodedPassword,customerDto.isEnabled());
        Customer customer = new Customer(customerDto.getId(),customerDto.getLastName(),customerDto.getFirstName(),customerDto.getGender(),customerDto.getPhone(),customerDto.getEmail());
        customerService.saveAccountCustomer(appUser);
        customerService.saveInfoCustomer(customer);
        return "login-register/login";
    }
}
