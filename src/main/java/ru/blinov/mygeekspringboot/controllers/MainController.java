package ru.blinov.mygeekspringboot.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("favicon.ico")
    public String appFavicon() {
        return "forward:/resources/static/favicon.ico";
    }
}
