package com.springboot.vegan.controller;

import com.springboot.vegan.model.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/table")
    public String showTable(Model model) {
        List<Recipe> list = getRecipesList();
        model.addAttribute("recipes", list);

        return "table";

    }

    @GetMapping("/detail")
    public String showDetail(Model model) {
        Recipe recipe1 = new Recipe();
        recipe1.setRecipeId(1);
        recipe1.setRecipeName("Breakfast - Blueberry banana pancakes");
        recipe1.setIngredients("Flour, baking powder, sugar, plant-milk");
        recipe1.setCookingTime(20);
        recipe1.setPublicationDate(new Date());

        model.addAttribute("recipe", recipe1);
        return "detail";
    }

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


    public List<Recipe> getRecipesList () {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        List<Recipe> recipesList = new LinkedList<Recipe>();

        try {
            // Create Recipe 1
            Recipe recipe1 = new Recipe();
            recipe1.setRecipeId(1);
            recipe1.setRecipeName("Breakfast - Blueberry banana pancakes");
            recipe1.setIngredients("Flour, baking powder, sugar, plant-milk");
            recipe1.setCookingTime(20);
            recipe1.setPublicationDate(sdf.parse("08-02-2020"));
            recipe1.setFeatured(1);
            recipe1.setImageMeal("blueberry-banana-pancakes.png");


            // Create Recipe 2
            Recipe recipe2 = new Recipe();
            recipe2.setRecipeId(2);
            recipe2.setRecipeName("Lunch - Spinach Basil Pasta");
            recipe2.setIngredients("Pasta, basil, garlic, olive oil");
            recipe2.setCookingTime(20);
            recipe2.setPublicationDate(sdf.parse("18-03-2021"));
            recipe2.setFeatured(0);
            recipe2.setImageMeal("Lunch-Vegan Pasta2.png");

            // Create Recipe 3
            Recipe recipe3 = new Recipe();
            recipe3.setRecipeId(3);
            recipe3.setRecipeName("Dinner - Mushroom Potato Soup");
            recipe3.setIngredients("Wild mushrooms, spring onions, potatoes, red pepper,olive oil, water");
            recipe3.setCookingTime(30);
            recipe3.setPublicationDate(sdf.parse("23-01-2022"));
            recipe3.setFeatured(0);

            // Create Recipe 4
            Recipe recipe4 = new Recipe();
            recipe4.setRecipeId(4);
            recipe4.setRecipeName("Smoothies - Fruits Smoothie");
            recipe4.setIngredients("Carrots, Apple, Ginger, Pineapple and a cup of Almond milk");
            recipe4.setCookingTime(15);
            recipe4.setPublicationDate(sdf.parse("08-07-2019"));
            recipe4.setFeatured(1);
            recipe4.setImageMeal("Smoothies-mixed fruits smoothie2.png");


            recipesList.add(recipe1);
            recipesList.add(recipe2);
            recipesList.add(recipe3);
            recipesList.add(recipe4);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return recipesList;

    }
}
