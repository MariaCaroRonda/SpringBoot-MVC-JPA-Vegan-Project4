package com.springboot.vegan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

    @GetMapping("/view/{id}")
    public String seeDetail(@PathVariable("id") int recipeId, Model model) {
        System.out.println("RecipeId: " + recipeId);

        // Find the recipe details on the DB

        model.addAttribute("recipeId", recipeId);
        return "recipes/detail";
    }
}
