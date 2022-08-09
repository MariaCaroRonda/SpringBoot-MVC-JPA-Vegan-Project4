package com.springboot.vegan.controller;

import com.springboot.vegan.model.Profile;
import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.service.ICategoriesService;
import com.springboot.vegan.service.IFavoritesService;
import com.springboot.vegan.service.IRecipesService;
import com.springboot.vegan.service.IUsersVgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

import java.util.*;

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

    private IFavoritesService favoritesService;


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

    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }


   @GetMapping("/orderRecByNameAsc")
    public String orderName(Model model) {
        List<Recipe> list = recipesService.findAllByNameAsc();

        model.addAttribute("recipes", list);


        return "home";
    }


    @GetMapping("/orderRecByNameDesc")
    public String orderNameDesc(Model model) {
        List<Recipe> list = recipesService.findAllByNameDesc();
        model.addAttribute("recipes", list);

        return "home";
    }

/**     @GetMapping("/orderbyNameDesc")
    public String orderNameAsc(Model model) {
        List<Recipe> list = recipesService.findAllByNameAsc();
        model.addAttribute("recipes", list);

        return "home";
    }*/

/**    @GetMapping("/orderbyNameAsc")
    public String orderNameAsc(Model model) {
        List<Recipe> list = recipesService.findAllByNameAsc();
        model.addAttribute("recipes", list);

        return "categories/listCategories";
    }*/

/**    @GetMapping("/orderbyNameDesc")
    public String orderNameDesc(Model model) {
        List<Recipe> list = recipesService.findAllByNameDesc();
        model.addAttribute("recipes", list);

        return "home";
    }*/


    @GetMapping("/signup")
    public String register(UserVegan userVegan) {
        return "formRegister";
    }

/*    @GetMapping("/signupEdit/{id}")
    public String registerEdit(@PathVariable("id") int userId,*//* UserVegan userVegan,*//* Model model,
                               HttpSession session,
                               Authentication authentication) {


        UserVegan userVegan1 = usersVgService.findById(userId);
        System.out.println(userVegan1);
        System.out.println(userVegan1.getProfiles());


        *//**String username = authentication.getName();*//*
        *//**userVegan = usersVgService.findByUsername(username);*//*

        *//**List<Profile> profiles = usersVgService.findProfilesUser(userVegan1.getUserId());*//*
        List<Profile> profiles = usersVgService.findProfilesUser(userId);

        //userVegan.setProfiles(usersVgService.findProfilesUser(userVegan.getUserId()));
        *//**userVegan1.setProfiles(profiles);*//*

        System.out.println("User before edit with profiles: " + userVegan1);
        System.out.println("Profiles@ " + userVegan1.getProfiles());
        model.addAttribute("userVegan", userVegan1);
        return "formRegisterEdit";
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


            System.out.println(userVegan);
            usersVgService.save(userVegan);
            attributes.addFlashAttribute("msg", "User registered successfully");
            return  "redirect:/login";
        } catch (Exception e) {
           // throw new RuntimeException(e);
            if (usersVgService.existUsername(userVegan.getUsername())) {
                attributes.addFlashAttribute("msg", "Username already in used. " +
                        "Please use a different Username.");
                return  "redirect:/signup";

            }

            if (usersVgService.existUserEmail(userVegan.getEmail())) {
                attributes.addFlashAttribute("msg", "Email already in used! " +
                        "Please use a different Email.");
                return  "redirect:/signup";

            }

           // attributes.addFlashAttribute("msg", ("Aan error has happened " + e.getMessage() ) );
           // attributes.addFlashAttribute("msg", "An error has happened ");
        }

            /**return  "redirect:/login";*/
            return  "redirect:/login";

    }

    @PostMapping("/update")
    public String updateRegistry(UserVegan userVegan, BindingResult result,
                                 RedirectAttributes attributes,
                                 Authentication auth, HttpSession session
                                ) {
        //return "redirect:/categories/index";

        /*Date date= userVegan.getRegistrationDate();*/

        /** Try this to avoid having a ',' at the end of the password*/
        /** If lenth > 60 && last character = ','  THEN remove last character ','*/
        /** ELSE encrypt new password */


        if (userVegan.getPassword().length() != 60) {
            String pwdPlain = userVegan.getPassword();
            String pwdEncrypt = passwordEncoder.encode(pwdPlain);
            userVegan.setPassword(pwdEncrypt);

        }


        String username = auth.getName();
        System.out.println("Username: " + username);

        System.out.println("pwd length: " + userVegan.getPassword().length());

        userVegan.setRegistrationDate(new Date());
        System.out.println(userVegan);
        /*System.out.println("Registration date: " + date);*/

        usersVgService.save(userVegan);
        return "redirect:/usersvegan/indexPaginate";
    }


/*    @PostMapping("/signupEdit")
    public String saveRegistryEdit(UserVegan userVegan, BindingResult result,
                               RedirectAttributes attributes) {


        *//** Test the below tutorial to check for duplicate usernames when singing up
         /* https://www.javaguides.net/2021/10/login-and-registration-rest-api-using-spring-boot-spring-security-hibernate-mysql-database.html *//*

        try {
            if (result.hasErrors()) {
                attributes.addFlashAttribute("An error has happened");
                System.out.println(userVegan + " error:  " + result.hasErrors());
                return "formRegisterEdit";
            }

           *//* String pwdPlain = userVegan.getPassword();
            String pwdEncrypt = passwordEncoder.encode(pwdPlain);

            userVegan.setPassword(pwdEncrypt);

            userVegan.setStatus(1); // 'Active' by default
            userVegan.setRegistrationDate(new Date());

            Profile profile = new Profile();
            profile.setProfileId(3); // Regular user by default
            userVegan.add(profile);*//*


            System.out.println("User vegan before save: " + userVegan);

            usersVgService.save(userVegan);
            attributes.addFlashAttribute("msg", "User updated successfully");

            return  "favorites/userProfile";

        } catch (Exception e) {
            // throw new RuntimeException(e);
            if (usersVgService.existUsername(userVegan.getUsername())) {
                System.out.println("msg error username: " + userVegan);
                attributes.addFlashAttribute("msg", "Username already in used. " +
                        "Please use a different Username.");
                 return  "redirect:/signupEdit";



            }

            if (usersVgService.existUserEmail(userVegan.getEmail())) {
                System.out.println("msg error email: " + userVegan);
                attributes.addFlashAttribute("msg", "Email already in used! " +
                        "Please use a different Email.");
                return  "redirect:/signupEdit";

            }

            // attributes.addFlashAttribute("msg", ("Aan error has happened " + e.getMessage() ) );
            // attributes.addFlashAttribute("msg", "An error has happened ");
        }

        *//**return  "redirect:/login";*//*
        return  "redirect:favorites/userProfile";

    }*/



/*    @GetMapping("/table")
    public String showTable(Model model) {
        List<Recipe> list = recipesService.findAll();
        model.addAttribute("recipes", list);

        return "table";

    }*/

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




    @ModelAttribute
    // We can add to the model all attributes we want, and these
    //attributes will be available for all methods within Home Controller
    public void setGenerics(Model model) {
        Recipe recipeSearch = new Recipe();
        recipeSearch.reset(); // Remove the default values to avoid run time errors
      /*  model.addAttribute("recipes", recipesService.findFeatured());*/
        model.addAttribute("recipes", recipesService.findAll());
        model.addAttribute("categories", categoriesService.findAll());
        model.addAttribute("search", recipeSearch);
    }


   @InitBinder
    public void initBinder(WebDataBinder binder) {
        // It set an empty string to null
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(dateFormat, false));


    }


    // We customize the Data Binding for all Date type properties
/*    @InitBinder
    public void initBinder2(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class,
                new CustomDateEditor(dateFormat, false));
    }*/

}
