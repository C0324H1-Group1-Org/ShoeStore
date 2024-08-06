package org.example.casestudymodule4shoestore.controllers.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {

    @GetMapping(value = "/login")
    public String loginPage(Model model, @RequestParam(value = "error", defaultValue = "")String error) {
        model.addAttribute("error", error);
        return "login-register/login";
    }

//    @GetMapping(value = "/logoutSuccessful")
//    public String logoutSuccessfulPage(Model model) {
//        System.out.println("out-----------");
//        model.addAttribute("title", "Logout");
//        return "login-register/login";
//    }

    @GetMapping("/403")
    public String forbiddenPage(Model model) {
        return "error/403";
    }

}
