package com.springboot.vegan.controller;

import com.springboot.vegan.model.Category;
import com.springboot.vegan.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/indexPaginate")
    public String showIndexPaginate(Model model, Pageable page) {
        Page<Category> list = categoriesService.findAll(page);
        model.addAttribute("categoriesPage", list);

        return "categories/listCategoriesPaginate";
    }

    @GetMapping("/orderByName")
    public String orderName(Model model) {
        List<Category> list = categoriesService.findAllByName();
        model.addAttribute("categories", list);

        return "categories/listCategories";

    }

    @GetMapping("orderNameDesc")
    public String orderNameDesc(Model model) {
        List<Category> list = categoriesService.findAllByNameDesc();
        model.addAttribute("categories", list);

        return "categories/listCategories";
    }

    @GetMapping("orderIdDesc")
    public String findAllById(Model model) {
        List<Category> list = categoriesService.findAllByIdDesc();
        model.addAttribute("categories", list);

        return "categories/listCategories";

    }

    @GetMapping("orderIdAsc")
    public String findAllByIdAsc(Model model) {
        List<Category> list = categoriesService.findAllByIdAsc();
        model.addAttribute("categories", list);

        return "categories/listCategories";

    }

/*    @GetMapping("/searchCat")
    public String search(@ModelAttribute("searchCat") Category category, Model model) {

        int catId = category.getCategoryId();
        System.out.println("CategoryId: " + category.getCategoryId());
        Category categorySearch = new Category();
        categorySearch = categoriesService.findById(category.getCategoryId());

        List<Category> list = categoriesService.findAllById(category.getCategoryId());
        List<Category> listAll = categoriesService.findAll();


        System.out.println(category.getCategoryId() + " " + category.getName());
        model.addAttribute("categories", list);

        model.addAttribute("categoriesAll", listAll);

        model.addAttribute("categorySearch", categorySearch);

        model.addAttribute("catId", catId);

      //  System.out.println("Searching all: " + categorySearch.getCategoryId());

        return "categories/listCategories";
    }*/

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable ("id") int categoryId, RedirectAttributes attributes) {
        System.out.println("Deleting category with id: " + categoryId);
        categoriesService.delete(categoryId);
        attributes.addFlashAttribute("msg", "Category deleted successfully");

        return "redirect:/categories/indexPaginate";
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


    @ModelAttribute
    public void setGenerics(Model model) {
        Category categorySearch = new Category();
        model.addAttribute("searchCat", categorySearch);
    }


/*    public String save(@RequestParam("name") String name,
                       @RequestParam("description") String description) {

        System.out.println("Category: " + name);
        System.out.println("Description: " + description);

        return "categories/listCategories";
    }*/
}
