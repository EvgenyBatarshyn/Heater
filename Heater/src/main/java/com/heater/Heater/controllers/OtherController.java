package com.heater.Heater.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Controller
public class OtherController {


    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }
    @GetMapping("/faq")
    public String faq(Model model) {
        model.addAttribute("title", "FAQ");
        return "faq";
    }
    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("title", "Контакты");
        return "contact";
    }


    @GetMapping("/404")
    public String error (Model model) {
        model.addAttribute("title", "Ошибка");
        return "404";
    }

}