package com.springboot.vegan.controller;

import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.service.IRecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

    @Autowired
    private IRecipesService recipesService;

    @GetMapping("/create")
    public String create(){
        return "recipes/formRecipe";
    }

    @PostMapping("/save")
    public String save(@RequestParam("name") String name,
                       @RequestParam("ingredients") String ingredients,
                       @RequestParam("premium") String premium,
                       @RequestParam("date") String date,
                       @RequestParam("featured") int featured,
                       @RequestParam("prepTime") int prepTime,
                       @RequestParam("cookingTime") int cookingTime,
                       @RequestParam("instructions") String instructions) {

        System.out.println("Name: " + name);
        System.out.println("Ingredients: " + ingredients);
        System.out.println("Premium: " + premium);
        System.out.println("Published: " + date);
        System.out.println("Featured: " + featured);
        System.out.println("Prep. time: " + prepTime);
        System.out.println("Cooking time: " + cookingTime);
        System.out.println("Instructions: " + instructions);

        return "recipes/listRecipes";
    }

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
