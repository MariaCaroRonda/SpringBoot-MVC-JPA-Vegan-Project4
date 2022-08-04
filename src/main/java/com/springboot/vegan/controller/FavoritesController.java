package com.springboot.vegan.controller;

import com.springboot.vegan.model.Favorite;
import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.service.IFavoritesService;
import com.springboot.vegan.service.IRecipesService;
import com.springboot.vegan.service.IUsersVgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    private IFavoritesService favoritesService;

    @Autowired
    private IRecipesService recipesService;

    @Autowired
    private IUsersVgService usersVgService;

/*    @GetMapping("/index")
    public String showIndex(Authentication auth, HttpSession session) {
        String username = auth.getName();
        System.out.println("User Name: " + username) ;

        for (GrantedAuthority role : auth.getAuthorities()) {
            System.out.println("ROLE :" + role.getAuthority());
        }

        if (session.getAttribute("userVegan") == null) {
            UserVegan userVegan = usersVgService.findByUsername(username);
            userVegan.setPassword(null); // to avoid store user's password in the session
            System.out.println("User: " + userVegan);
            session.setAttribute("userVegan", userVegan);
        }

        return "favorites/listFavorites";
    }*/


    @GetMapping("/index")
    public String showIndex() {
        return "favorites/listFavorites";
    }

    @GetMapping("/indexPaginate")
    public String showIndexPaginate() {
        return "favorites/listFavoritesPaginate";
    }

    @GetMapping("create/{idFavorite}")
    public String create() {
        return "favorites/formFavorite";

    }

    public String save() {
        return "redirect:/";
    }

    public String delete () {
        return "redirect/favorites/indexPaginate";
    }

/*    @PostMapping("/save/{id}")
    public String save(Favorite favorite, Model model,
                       HttpSession session,
                       @PathVariable("id") Integer recipeId,
                       Authentication auth) {

        // User who started the session
        String username = auth.getName();

        UserVegan user = usersVgService.findByUsername(username);
        Recipe recipe = recipesService.findById(recipeId);

        favorite.setUserVegan(user);
        favorite.setRecipe(recipe);
        favorite.setComments(recipe.getName());

        favoritesService.save(favorite);

        System.out.println(favorite);

        return "favorites/listFavorites";

    }*/
}
