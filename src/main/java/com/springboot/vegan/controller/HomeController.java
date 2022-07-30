package com.springboot.vegan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/listRecipes")
    public String showList(Model model){
        List<String> list = new LinkedList<String>();

        list.add("Breakfast - Blueberry banana pancakes");
        list.add("Lunch - Spinach Basil Pasta");
        list.add("Dinner - Mushroom Potato Soup");
        list.add("Smoothies - Fruits Smoothie");

        model.addAttribute("recipes", list);

        return "listRecipes";
    }

    @GetMapping("/")
    public String howHome(Model model) {
/*        model.addAttribute("message", "Welcome to Vegan App");
        model.addAttribute("date", new Date());*/

        String recipeName = "Brownies";
        Date publicationDate = new Date();
        int cookingTime = 60;
        boolean premiumRecipe = true;

        model.addAttribute("recipeName", recipeName);
        model.addAttribute("publicationDate", publicationDate);
        model.addAttribute("cookingTime", cookingTime);
        model.addAttribute("premiumRecipe", premiumRecipe);

        return "home";
    }
}
