package com.springboot.vegan.controller;

import com.springboot.vegan.model.Profile;
import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.service.ICategoriesService;
import com.springboot.vegan.service.IRecipesService;
import com.springboot.vegan.service.IUsersVgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ICategoriesService categoriesService;

    @Autowired
    private IRecipesService recipesService;

    @Autowired
    private IUsersVgService usersVgService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String showHome(Model model) {
        // we don't need the below lines when we use setGenerics()
/*        List<Recipe> list = recipesService.findAll();
        model.addAttribute("recipes", list);*/

        return "home";
    }

    @GetMapping("/index")
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

        return "redirect:/";
    }

    @GetMapping("/signup")
    public String register(UserVegan userVegan) {
        return "formRegister";
    }
/**    @PostMapping("/signup")
    public String saveRegistry(UserVegan userVegan,
                               RedirectAttributes attributes) {

        String pwdPlain = userVegan.getPassword();
        String pwdEncrypt = passwordEncoder.encode(pwdPlain);

        userVegan.setPassword(pwdEncrypt);

        userVegan.setStatus(1); // 'Active' by default
        userVegan.setRegistrationDate(new Date());

        Profile profile = new Profile();
        profile.setProfileId(3); // Regular user by default
        userVegan.add(profile);

        usersVgService.save(userVegan);
        attributes.addFlashAttribute("msg", "User registered successfully");

        *//*return "redirect:/usersvegan/index";*//*
        return  "redirect:/login";

    }*/

/*    @PostMapping(value="/signup")
    public String guardarRegistro(Usuario usuario, BindingResult result, RedirectAttributes attributes) {
        try {

            if (result.hasErrors()) {
                return "usuarios/formRegistro";
            }
            // la logica restante
            //
            usuariosService.guardar(usuario);

            attributes.addFlashAttribute("msg", "Los datos del usuario fueron guardados.");
        }catch(Exception ex) {
            attributes.addFlashAttribute("msg", "Ocurrio un error durante la operación. " + ex.getMessage());
        }
        return "redirect:/usuarios/index";
    }*/

    @PostMapping("/signup")
    public String saveRegistry(UserVegan userVegan, BindingResult result,
                               RedirectAttributes attributes) {

        /** Test the below tutorial to check for duplicate usernames when singing up
       /* https://www.javaguides.net/2021/10/login-and-registration-rest-api-using-spring-boot-spring-security-hibernate-mysql-database.html */

        try {
            if (result.hasErrors()) {
                attributes.addFlashAttribute("An error has happened");
                return "formRegister";
            }

            String pwdPlain = userVegan.getPassword();
            String pwdEncrypt = passwordEncoder.encode(pwdPlain);

            userVegan.setPassword(pwdEncrypt);

            userVegan.setStatus(1); // 'Active' by default
            userVegan.setRegistrationDate(new Date());

            Profile profile = new Profile();
            profile.setProfileId(3); // Regular user by default
            userVegan.add(profile);

            usersVgService.save(userVegan);
            attributes.addFlashAttribute("msg", "User registered successfully");
        } catch (Exception e) {
            /*throw new RuntimeException(e);*/
            attributes.addFlashAttribute("msg", "Han error has happened " + e.getMessage());
            attributes.addFlashAttribute("msg", "Han error has happened ");
        }

            /**return  "redirect:/login";*/
            return  "redirect:/signup";

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

    @GetMapping("/search")
    public String search(@ModelAttribute("search") Recipe recipe, Model model) {
        System.out.println("Searching by: " + recipe);

        // It uses a search type 'like'
        //where ingredients like '%?%'
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("ingredients",
                        ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Recipe> example = Example.of(recipe, matcher);
        List<Recipe> list = recipesService.findByExample(example);
        model.addAttribute("recipes", list);

        return "home";
    }

    @GetMapping("/login" )
    public String showLogin() {
        return "formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler =
                new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/login";
    }


    @GetMapping("/bcrypt/{text}")
    @ResponseBody
    public String encrypt(@PathVariable("text") String text) {
        return text + " Encrypted with Bcrypt: " + passwordEncoder.encode(text);
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // It set an empty string to null
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute
    // We can add to the model all attributes we want, and these
    //attributes will be available for all methods within Home Controller
    public void setGenerics(Model model) {
        Recipe recipeSearch = new Recipe();
        recipeSearch.reset(); // Remove the default values to avoid run time errors
        model.addAttribute("recipes", recipesService.findFeatured());
        model.addAttribute("categories", categoriesService.findAll());
        model.addAttribute("search", recipeSearch);
    }

}
