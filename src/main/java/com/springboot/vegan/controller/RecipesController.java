package com.springboot.vegan.controller;

import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.service.ICategoriesService;
import com.springboot.vegan.service.IRecipesService;
import com.springboot.vegan.util.MyUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

    @Value("${veganapp.path.images}")
    private String path;

    @Autowired
    private IRecipesService recipesService;

    @Autowired
    private ICategoriesService categoriesService;


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
    public String create(Recipe recipe, Model model){
        model.addAttribute("categories", categoriesService.findAll());

        return "recipes/formRecipe";
    }

    @PostMapping("/save")
    public String save(Recipe recipe, BindingResult result, RedirectAttributes attributes,
                       @RequestParam("fileImage") MultipartFile multiPart) {
        if (result.hasErrors()) {
            for (ObjectError error: result.getAllErrors()){
                System.out.println("An error has happened: "+ error.getDefaultMessage());
            }
            return "recipes/formRecipe";
        }

        if (!multiPart.isEmpty()) {
            /*String path = "c:/vegan/img-vegan04/"; // Windows*/

            String nameImage = MyUtilities.saveFile(multiPart, path);
            if (nameImage != null){ // The image was uploaded
                // Process file nameImage
                recipe.setImageMeal(nameImage);
            }
        }

        recipesService.save(recipe);
        attributes.addFlashAttribute("msg", "Recipe Saved Successfully");

        return "redirect:/recipes/index";
    }


/*
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int recipeId, Model model) {
        System.out.println("Deleting recipe with Id: " + recipeId);

        model.addAttribute("id", recipeId);
        return "message";
    }
*/

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int recipeId, RedirectAttributes attributes) {
        System.out.println("Deleting recipe with id: " + recipeId);
        recipesService.delete(recipeId);
        attributes.addFlashAttribute("msg", "Recipe deleted successfully");

        return "redirect:/recipes/index";
    }

    @GetMapping("/view/{id}")
    public String seeDetail(@PathVariable("id") int recipeId, Model model) {

        Recipe recipe = recipesService.findById(recipeId);

        model.addAttribute("recipe", recipe);
       //System.out.println("Recipe: " + recipe);

        // Find the recipe details on the DB
        return "detail";
    }


/*    public String edit() {}*/

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class,
                new CustomDateEditor(dateFormat, false));
    }
}
