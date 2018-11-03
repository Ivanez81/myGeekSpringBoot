package ru.blinov.mygeekspringboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showMyLoginPage() {
        return "modern-login";
    }

    @GetMapping("/accessDenied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }

//    @GetMapping("/ohSh")
//    public String ohSh() {
//        return "plain-login";
//    }
}
