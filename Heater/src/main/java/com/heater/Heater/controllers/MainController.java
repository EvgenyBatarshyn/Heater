package com.heater.Heater.controllers;

import com.heater.Heater.models.WaterHeater;
import com.heater.Heater.rep.WaterHeaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/adminpage")
    public String admin(Model model) {
        Iterable<WaterHeater> waterHeaters = waterHeaterRepository.findAll();
        model.addAttribute("wh",waterHeaters);
        return "tables";
    }

    @GetMapping("/addpage")
    public String addpage(Model model) {
        return "forms";
    }

    @PostMapping("/addpage/add")
    public String addproduct( @RequestParam String name, @RequestParam String description, @RequestParam int price, @RequestParam String photo,  Model model) {
        WaterHeater post = new WaterHeater(name,  description, price, photo);
        waterHeaterRepository.save(post);
        return "redirect:/addpage";
    }

    @GetMapping("/product/{id}/edit")
    public String edit(@PathVariable(value = "id") long id, Model model) {
        Optional<WaterHeater> waterHeater = waterHeaterRepository.findById(id);
        ArrayList<WaterHeater> res = new ArrayList<>();
        waterHeater.ifPresent(res::add);
        model.addAttribute("product",res);
        return "edit";
    }

    @PostMapping("/product/{id}/edit")
    public String productedit( @PathVariable(value="id") long id,@RequestParam String name, @RequestParam String description, @RequestParam int price, @RequestParam String photo,  Model model) {
        WaterHeater post = waterHeaterRepository.findById(id).orElseThrow();
        post.setName(name);
        post.setDescription(description);
        post.setPrice(price);
        post.setPhoto(photo);
        waterHeaterRepository.save(post);
        return "redirect:/adminpage";
    }

    @PostMapping("/product/{id}/remove")
    public String productremove( @PathVariable(value="id") long id,  Model model) {
        WaterHeater post = waterHeaterRepository.findById(id).orElseThrow();
        waterHeaterRepository.delete(post);
        return "redirect:/adminpage";
    }


}