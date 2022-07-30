package com.springboot.vegan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String save(@RequestParam("name") String name,
                       @RequestParam("description") String description) {

        System.out.println("Category: " + name);
        System.out.println("Description: " + description);

        return "categories/listCategories";
    }
}
