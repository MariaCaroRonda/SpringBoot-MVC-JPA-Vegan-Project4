package com.springboot.vegan.controller;

import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.service.IUsersVgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int userId, RedirectAttributes attributes) {
        usersVgService.delete(userId);
        attributes.addFlashAttribute("msg", "User deleted successfully");
        return "redirect:/usersvegan/index";
    }

/*    @ModelAttribute
    public void setGenerics(Model model) {
        model.addAttribute("usersvg", usersVgService.findAll());
    }*/

}
