package com.heater.Heater.controllers;

import com.heater.Heater.models.WaterHeater;
import com.heater.Heater.rep.WaterHeaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class MainController {

    @Autowired
    private WaterHeaterRepository waterHeaterRepository;


    @GetMapping("/")
    public String home(Model model) {
        Iterable<WaterHeater> waterHeaters = waterHeaterRepository.findAll();
        model.addAttribute("wh",waterHeaters);
        return "home";
    }

    @GetMapping("/products")
    public String products(Model model) {
        Iterable<WaterHeater> waterHeaters = waterHeaterRepository.findAll();
        model.addAttribute("wh",waterHeaters);
        return "products";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable(value = "id") long id, Model model) {
        Optional<WaterHeater> waterHeater = waterHeaterRepository.findById(id);
        ArrayList<WaterHeater> res = new ArrayList<>();
        waterHeater.ifPresent(res::add);
        model.addAttribute("product",res);
        return "product";
    }


    @GetMapping("/admin")
    public String admin() {
        return "home";
    }

    @GetMapping("/user")
    public String user() {
        return "home";
    }



}