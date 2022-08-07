package com.springboot.vegan.controller;

import com.springboot.vegan.model.Category;
import com.springboot.vegan.service.ICategoriesService;
import com.springboot.vegan.service.IRecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/categories")
public class CategoriesController {

    @Autowired
    private ICategoriesService categoriesService;

    // @GetMapping
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndex(Model model) {
        List<Category> list = categoriesService.findAll();
        model.addAttribute("categories", list);
        return "categories/listCategories";
    }

/*    @GetMapping("/indexPaginate")
    public String showIndexPaginate(Model model) {

        return findPaginated(1,  "name", "asc", model);

        *//*List<Category> list = categoriesService.findAll();
        model.addAttribute("categories", list);
        return "categories/listCategoriesPaginate";*//*
    }*/

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable ("id") int categoryId, RedirectAttributes attributes) {
        System.out.println("Deleting category with id: " + categoryId);
        categoriesService.delete(categoryId);
        attributes.addFlashAttribute("msg", "Category deleted successfully");

        return "redirect:/categories/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int categoryId, Model model) {
        Category category = categoriesService.findById(categoryId);
        model.addAttribute("category", category);

        return "categories/formCategory";
    }


    // @GetMapping
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Category category) {
        return "categories/formCategory";
    }

    // @PostMapping
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Category category, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            for (ObjectError error: result.getAllErrors()) {
                System.out.println("An error has happened: " + error.getDefaultMessage());
            }
            return "categories/formCategory";
        }

        categoriesService.save(category);
        attributes.addFlashAttribute("msg", "Category Saved Successfully");

        return "redirect:/categories/index";
    }

/*    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Category> page = categoriesService.findPagination(pageNo, pageSize, sortField, sortDir);
        List<Category> listCategories = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCategories", listCategories);

        return "categories/indexPaginate";
    }*/



/*    public String save(@RequestParam("name") String name,
                       @RequestParam("description") String description) {

        System.out.println("Category: " + name);
        System.out.println("Description: " + description);

        return "categories/listCategories";
    }*/
}
