package com.springboot.vegan.controller;

import com.springboot.vegan.model.Profile;
import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.service.IRecipesService;
import com.springboot.vegan.service.IUsersVgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private IRecipesService recipesService;

    @Autowired
    private IUsersVgService usersVgService;


    @GetMapping("/")
    public String showHome(Model model) {
        // we don't need the below lines when we use setGenerics()
/*        List<Recipe> list = recipesService.findAll();
        model.addAttribute("recipes", list);*/

        return "home";
    }

    @GetMapping("/signup")
    public String register(UserVegan userVegan) {
        return "formRegister";
    }
    @PostMapping("/signup")
    public String saveRegistry(UserVegan userVegan,
                               RedirectAttributes attributes) {
        userVegan.setStatus(1); // 'Active' by default
        userVegan.setRegistrationDate(new Date());

        Profile profile = new Profile();
        profile.setProfileId(3); // Regular user by default
        userVegan.add(profile);

/*        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("An error has occurred: " + error.getDefaultMessage());
            }
        }*/

        usersVgService.save(userVegan);
        attributes.addFlashAttribute("msg", "User registered successfully");

        return "redirect:/usersvegan/index";

    }

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
        recipe1.setName("Breakfast - Blueberry banana pancakes");
        recipe1.setIngredients("Flour, baking powder, sugar, plant-milk");
        recipe1.setCookingTime(20);
        recipe1.setDate(new Date());

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



    @ModelAttribute
    // We can add to the model all attributes we want, and these
    //attributes will be available for all methods within Home Controller
    public void setGenerics(Model model) {
        model.addAttribute("recipes", recipesService.findFeatured());
    }

}
