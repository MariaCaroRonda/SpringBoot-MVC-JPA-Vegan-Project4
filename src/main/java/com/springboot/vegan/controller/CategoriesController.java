package com.springboot.vegan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/categories")
public class CategoriesController {

    // @GetMapping
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndex(Model model) {

        return "categories/listCategories";
    }

    // @GetMapping
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "categories/formCategory";
    }

    // @PostMapping
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save() {
        return "categories/listCategories";
    }
}
