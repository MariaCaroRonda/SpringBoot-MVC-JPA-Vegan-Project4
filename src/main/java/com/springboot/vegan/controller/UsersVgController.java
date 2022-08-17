package com.springboot.vegan.controller;

import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.service.IUsersVgService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/usersvegan")
public class UsersVgController {

    @Autowired
    private IUsersVgService usersVgService;

    @GetMapping( "/index")
    public String showIndex(Model model) {
        List<UserVegan> list = usersVgService.findAll();
        model.addAttribute("usersvegan", list);
        return "usersvegan/listUsersVegan";
    }

    @GetMapping("/indexPaginate")
    public String showIndexPaginate(Model model, Pageable page) {

/*        Page<UserVegan> page = usersVgService.findAllPaginate();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<UserVegan> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("listUsersPaginate", list);*/
        Page<UserVegan> list = usersVgService.findAll(page);
        model.addAttribute("usersPage", list);

        return "usersvegan/listUsersVeganPaginate";
    }

    @GetMapping("/search")
    public String search(@ModelAttribute("search") UserVegan userVegan, Pageable page, Model model) {

        System.out.println("Searching by: " + userVegan.getFirstName() + " " + userVegan.getEmail());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("firstName",
                        ExampleMatcher.GenericPropertyMatchers.contains());

        Example<UserVegan> example = Example.of(userVegan, matcher);

        Page<UserVegan> list = usersVgService.findAllExamplePage(example, page);
        model.addAttribute("usersPage", list);

        /******************/

        return "usersvegan/listUsersVeganPaginate";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int userId, RedirectAttributes attributes) {
        usersVgService.delete(userId);
        attributes.addFlashAttribute("msg", "User deleted successfully");
        return "redirect:/usersvegan/indexPaginate";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int userId,

                       Model model) {
        UserVegan userVegan = usersVgService.findById(userId);
       model.addAttribute("userVegan", userVegan);
       model.addAttribute("userId", userId);
       /**model.addAttribute("uservegan", new UserVegan());*/

        System.out.println(userVegan.getFirstName());
        System.out.println(userVegan.getPassword());
        System.out.println("pwd length: " + userVegan.getPassword().length());

    //    System.out.println(password);

        return "formRegisterEdit";
    }


    @ModelAttribute
    public void setGenerics(Model model) {
        UserVegan userSearch = new UserVegan();
        model.addAttribute("search", userSearch);
    }

/*    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // It set an empty string to null
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }*/

/*    @ModelAttribute
    public void setGenerics(Model model) {
        model.addAttribute("usersvg", usersVgService.findAll());
    }*/

}
