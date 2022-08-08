package com.springboot.vegan.controller;

import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.service.ICategoriesService;
import com.springboot.vegan.service.IRecipesService;
import com.springboot.vegan.service.IUsersVgService;
import com.springboot.vegan.util.MyUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Value("${veganapp.path.images}")  // path where the uploaded images are saved
    private String path;

    @Autowired
    private IRecipesService recipesService;

    @Autowired
    private ICategoriesService categoriesService;

    @Autowired
    private IUsersVgService usersVgService;


/*  // Home Page without Pagination
    @GetMapping("/index")
    public String showIndex(Model model) {
        List<Recipe> list = recipesService.findAll();
        model.addAttribute("recipes", list);

        return "recipes/listRecipes";
    }*/


    /** Home Page with Pagination.
     * It displays a list of Recipes sorted in Ascending Order by Recipe Name*/
    @GetMapping("/indexPaginate")
    public String showIndexPaginate(Model model, Pageable page) {
        Page<Recipe> list = recipesService.findAll(page);
        model.addAttribute("recipesPage", list);

        return "recipes/listRecipesPaginate";
    }




    /** Method that displays a form to create a new Recipe */
    @GetMapping("/create")
    public String create(Recipe recipe, Model model){

        return "recipes/formRecipe";
    }

    /** Method to save a Recipe in the Database */
    @PostMapping("/save")
    public String save(Recipe recipe, BindingResult result, RedirectAttributes attributes,
                       @RequestParam("fileImage") MultipartFile multiPart) {

        if (result.hasErrors()) {
            // Print in the console error messages if they have occurred
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

        // Save object Recipe on the Database and display a confirmation message
        recipesService.save(recipe);
        attributes.addFlashAttribute("msg", "Recipe Saved Successfully");

        return "redirect:/recipes/indexPaginate";
    }


    // Delete a Recipe from the Database and display a confirmation message
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int recipeId, RedirectAttributes attributes) {
        System.out.println("Deleting recipe with id: " + recipeId);
        recipesService.delete(recipeId);
        attributes.addFlashAttribute("msg",
                "Recipe deleted successfully");

        return "redirect:/recipes/indexPaginate";
    }

    // Find the recipe details on the DB
    @GetMapping("/view/{id}")
    public String seeDetail(@PathVariable("id") int recipeId, Model model) {

        Recipe recipe = recipesService.findById(recipeId);
        model.addAttribute("recipe", recipe);

        return "detail";
    }

    // Method that renders an HTML form to edit an existing Recipe on the DB
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int recipeId, Model model) {
        Recipe recipe = recipesService.findById(recipeId);
        model.addAttribute("recipe", recipe);
        model.addAttribute("categories", categoriesService.findAll());

        return "recipes/formRecipe";
    }


    @GetMapping("/search")
    public String search(@ModelAttribute("search") Recipe recipe, Pageable page, Model model) {
        System.out.println("Searching by: " + recipe.getCategory().getCategoryId() +
                " " + recipe.getName() + " " + recipe.getImageMeal());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name",
                        ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Recipe> example = Example.of(recipe, matcher);

        Page<Recipe> list = recipesService.findAllExamplePage(example, page);
        model.addAttribute("recipesPage", list);

        return "recipes/listRecipesPaginate";
    }



    // Generic method to add a Model to the Category List. It is used in create()
    //and in edit() methods
    @ModelAttribute
    public void setGenerics(Model model) {
        model.addAttribute("categories", categoriesService.findAll());
        Recipe recipeSearch = new Recipe();
        recipeSearch.reset();
        model.addAttribute("search", recipeSearch);
    }


    // We customize the Data Binding for all Date type properties
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class,
                new CustomDateEditor(dateFormat, false));
    }
}
