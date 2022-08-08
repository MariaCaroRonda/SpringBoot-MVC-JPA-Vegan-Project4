package com.springboot.vegan.controller;

import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.service.IUsersVgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
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
    public String showIndexPaginate(Model model) {

        Page<UserVegan> page = usersVgService.findAllPaginate();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<UserVegan> list = page.getContent();

/*        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalItems", totalItems);*/
        model.addAttribute("listUsersPaginate", list);

        return "usersvegan/listUsersVeganPaginate";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int userId, RedirectAttributes attributes) {
        usersVgService.delete(userId);
        attributes.addFlashAttribute("msg", "User deleted successfully");
        return "redirect:/usersvegan/index";
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
