package com.springboot.vegan.controller;

import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.service.IRecipesService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IRecipesService recipesService;

    @GetMapping("/table")
    public String showTable(Model model) {
        List<Recipe> list = recipesService.findAll();
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

}
