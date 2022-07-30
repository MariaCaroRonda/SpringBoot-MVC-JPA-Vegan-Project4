package com.springboot.vegan.controller;

import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.service.IRecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

    @Autowired
    private IRecipesService recipesService;

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int recipeId, Model model) {
        System.out.println("Deleting recipe with Id: " + recipeId);

        model.addAttribute("id", recipeId);
        return "message";
    }

    @GetMapping("/view/{id}")
    public String seeDetail(@PathVariable("id") int recipeId, Model model) {

        Recipe recipe = recipesService.findById(recipeId);

        model.addAttribute("recipe", recipe);
        System.out.println("Recipe: " + recipe);

        // Find the recipe details on the DB
        return "detail";
    }
}
