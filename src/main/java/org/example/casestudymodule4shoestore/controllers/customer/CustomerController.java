package org.example.casestudymodule4shoestore.controllers.customer;

import org.example.casestudymodule4shoestore.dtos.customer.CustomerDto;
import org.example.casestudymodule4shoestore.models.*;
import org.example.casestudymodule4shoestore.repositories.login.IVerificationTokenRepository;
import org.example.casestudymodule4shoestore.services.customer.ICustomerService;
import org.example.casestudymodule4shoestore.services.login.IAppRoleService;
import org.example.casestudymodule4shoestore.services.login.IUserRoleService;
import org.example.casestudymodule4shoestore.services.login.IUserService;
import org.example.casestudymodule4shoestore.services.login.UserService;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IAppRoleService appRoleService;

    @Autowired
    private UserService userService;

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
//      Check tài khoản, và sđt khi đăng ký
        if (customerService.phoneAndEmailExists(customerDto.getEmail(), customerDto.getPhone())) {
            model.addAttribute("customerDto", customerDto);
            model.addAttribute("emailError", "Tên đăng nhập đã tồn tại");
            model.addAttribute("phoneError", "Số điện thoại đã tồn tại");
            return "login-register/register";
        }
//      check password có giống nhau k
        if (!confirmPassword.equals(customerDto.getPassword())) {
            model.addAttribute("customerDto", customerDto);
            model.addAttribute("checkPassword", "Mật khẩu không khớp!");
            return "login-register/register";
        }
//      mã hoá password
        String encodedPassword = passwordEncoder.encode(customerDto.getPassword());
//      lưu thông tin đăng nhập user
        AppUser appUser = new AppUser();
        appUser.setUserName(customerDto.getEmail());
        appUser.setEncrytedPassword(encodedPassword);
        appUser.setEnabled(customerDto.isEnabled());
        customerService.saveAccountCustomer(appUser);
        // xác nhận role user
        userService.registerUser(appUser);
        userRoleService.save(appUser, appRoleService.findByRoleName("ROLE_USER"));
        Customer customer = new Customer();
        // lưu thông tin customer
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setGender(customerDto.getGender());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customer.setAppUser(appUser);
        customerService.saveInfoCustomer(customer);
        return "login-register/login";
    }
}
