package com.springboot.vegan.controller;

import com.springboot.vegan.model.Favorite;
import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.service.IFavoritesService;
import com.springboot.vegan.service.IRecipesService;
import com.springboot.vegan.service.IUsersVgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    private IFavoritesService favoritesService;

    @Autowired
    private IRecipesService recipesService;

    @Autowired
    private IUsersVgService usersVgService;


    @GetMapping("/index")
    public String showIndex(Model model,
                            HttpSession session,
                            Authentication authentication) {

        String username = authentication.getName();
        System.out.println("Username: " + username);

        // Find user's favorite on DB
        UserVegan user = usersVgService.findByUsername(username);
        int userId = user.getUserId();
      /**  user.setPassword(null);*/

        List<Favorite> list = favoritesService.findAll();
       // model.addAttribute("favorites", list);

        List<Favorite> list2 = favoritesService.findByUser(user);
        model.addAttribute("favorites", list2);

        System.out.println(user.getFirstName() + " List of favorites ");
        for (Favorite fav : list2) {
            System.out.println("Recipe name: " + fav.getRecipe().getName());
        }

        return "favorites/listFavorites";
    }


    @GetMapping("/indexPaginate")
    public String showIndexPaginate() {
        return "favorites/listFavoritesPaginate";
    }

/*    @GetMapping("/indexAdmin")
    public String showIndexAdmin(Model model) {
        List<Favorite> list = favoritesService.findAll();
        model.addAttribute("favorites", list);
        return "favorites/listFavoritesAdmin";
    }*/

    @GetMapping("/indexAdmin")
    public String showIndexAdmin(Model model, Pageable page) {

        Page<Favorite> list2 = favoritesService.findAll(page);
        List<Favorite> list = favoritesService.findAll();
      //  model.addAttribute("favorites", list);
        model.addAttribute("favoritesPaginate", list2);
        return "favorites/listFavoritesAdmin";
    }

    // Form to add a Recipe to User's Favorites
    @GetMapping("/create/{recipeId}")
    public String create(Favorite favorite,
                         @PathVariable Integer recipeId,
                         Model model
                         //HttpSession session,
                         //Authentication authentication
    ) {

        Recipe recipe = recipesService.findById(recipeId);
        model.addAttribute("recipe", recipe);

        return "favorites/formFavorite";
    }


    @PostMapping("/save")
    public String save(Favorite favorite,
                          BindingResult result,
                          Model model,
                          HttpSession session,
                          RedirectAttributes attributes,
                          Authentication authentication) {

        // Recover username who started the session
        String username = authentication.getName();

        if (result.hasErrors()){
            System.out.println("Errors in formFavorite");
            return "favorites/formFavorite";
        }


        // Find the object UserVegan on the DB
        UserVegan userVegan = usersVgService.findByUsername(username);
        System.out.println(userVegan.getFirstName());

        // Reference the Favorite User field
        favorite.setUserVegan(userVegan);

        System.out.println("Favorite: " + favorite);

        // Save object favorite on the DB
        if (favoritesService.isRecipePresentUserFavorite(
                favorite.getRecipe().getRecipeId(),
                favorite.getUserVegan().getUserId())  ){
            System.out.println("Recipe with Id " + favorite.getRecipe().getRecipeId() +
                    " and user " + favorite.getUserVegan().getUserId() + " already exist");
            attributes.addFlashAttribute("msg", "Recipe was already in Favorites!");
        }
        else {
            System.out.println("Saving ");
            favoritesService.save(favorite);
            attributes.addFlashAttribute("msg", "Recipe added to Favorites successfully");
        }


         return "redirect:/favorites/index";
       /* return "redirect:/";*/
    }


    @GetMapping("/userProfile")
    public String showProfile (Model model,
                               HttpSession session,
                               Authentication authentication) {

        String username = authentication.getName();
        UserVegan user = usersVgService.findByUsername(username);
        /**user.setPassword(null);*/

        System.out.println(user);
        model.addAttribute("user", user);

        return "favorites/userProfile";
    }

    @GetMapping("/view/{id}")
    public String seeDetail(@PathVariable("id") int recipeId,
                            Model model) {

        Recipe recipe = recipesService.findById(recipeId);
        model.addAttribute("recipe", recipe);

        return "favorites/detailFavorite";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable ("id") int favoriteId, RedirectAttributes attributes) {

        System.out.println("Deleting favorite with id: " + favoriteId);
        favoritesService.delete(favoriteId);


        return "redirect:/favorites/index";
    }

    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable ("id") int favoriteId, RedirectAttributes attributes) {

        System.out.println("Deleting favorite with id: " + favoriteId);
        favoritesService.delete(favoriteId);


        return "redirect:/favorites/indexAdmin";
    }


    @GetMapping("/editProfile/{id}")
    public String editProfile(@PathVariable("id") int userId, Model model) {

        UserVegan userVegan = usersVgService.findById(userId);

        model.addAttribute("userVegan", userVegan);


        return  "favorites/formRegisterEdit";
    }

    @PostMapping("/update")
    public String save(UserVegan userVegan, BindingResult result,
                       RedirectAttributes attributes) {



        usersVgService.save(userVegan);

        return "redirect:/";
    }


}
