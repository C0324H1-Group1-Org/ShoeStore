package org.example.casestudymodule4shoestore.controllers.register;

import org.example.casestudymodule4shoestore.dtos.register.UserDto;
import org.example.casestudymodule4shoestore.models.login.AppUser;
import org.example.casestudymodule4shoestore.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @GetMapping("/register")
    public String showFormRegister(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login-register/register";
    }

    @PostMapping("/register")
    public String register( @Validated @ModelAttribute UserDto userDto,
                            BindingResult result,
                            Model model) {
        model.addAttribute("isSubmitted", true);
        if (result.hasFieldErrors()) {
            model.addAttribute("userDto", userDto);
            return "login-register/register";
        }
        //check tk có tồn tại hay k
        if (userService.userExists(userDto.getName())) {
            model.addAttribute("userDto", userDto);
            model.addAttribute("registrationError", "Tên đăng nhập đã tồn tại");
            return "login-register/register";
        }
        //set cho tk hoạt động
        userDto.setEnabled(true);
        //mã hoá password
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        AppUser appUser = new AppUser(userDto.getId(),userDto.getName(),encodedPassword,userDto.isEnabled());
        userService.save(appUser);
        return "login-register/login";
    }
}
