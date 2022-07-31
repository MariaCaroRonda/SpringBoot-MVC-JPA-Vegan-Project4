package com.springboot.vegan.controller;

import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.service.IRecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

    @Autowired
    private IRecipesService recipesService;


    @GetMapping("/index")
    public String showIndex(Model model) {
        List<Recipe> list = recipesService.findAll();
        for (Recipe tmpRecipe : list) {
            System.out.println(tmpRecipe);
        }
        model.addAttribute("recipes", list);

        return "recipes/listRecipes";
    }

    @GetMapping("/create")
    public String create(Recipe recipe){
        return "recipes/formRecipe";
    }

    @PostMapping("/save")
    public String save(Recipe recipe, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            for (ObjectError error: result.getAllErrors()){
                System.out.println("An error has happened: "+ error.getDefaultMessage());
            }
            return "recipes/formRecipe";

        }
        recipesService.save(recipe);
        attributes.addFlashAttribute("msg", "Recipe Saved Successfully");

        return "redirect:/recipes/index";
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

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class,
                new CustomDateEditor(dateFormat, false));
    }
}
