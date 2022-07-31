package com.springboot.vegan;

import com.springboot.vegan.model.Category;
import com.springboot.vegan.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringBootMvcJpaVeganProject4Application implements CommandLineRunner {

    /** Test the Category Repository */

    @Autowired
    private CategoriesRepository categoriesRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvcJpaVeganProject4Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*  save();
            findById();
            update();
            delete();
            save();
            countCategories();
            deleteAll(); */

        findByIds();
    }

    private void findByIds() {
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(1);
        ids.add(4);
        ids.add(8);
        Iterable<Category> categories = categoriesRepository.findAllById(ids);
        for (Category tmpCat : categories) {
            System.out.println(tmpCat);
        }
    }

    private void deleteAll() {
        categoriesRepository.deleteAll();
    }

    private void countCategories() {
        long countCat = categoriesRepository.count();
        System.out.println("Total categories: " + countCat);
    }

    public void delete() {
        int categoryId = 1;
        if (!categoriesRepository.findById(categoryId).isPresent()) {
            System.out.println("Category with id " + categoryId + " not found");
        }
        else
        {
            categoriesRepository.deleteById(categoryId);
        }
    }

    /* If the category exists, it updates the category
    * If the category doesn't exist, it creates a new one */
    private void update() {
        Optional<Category> optional = categoriesRepository.findById(3);
        if (optional.isPresent()) {
            Category tmpCat = optional.get();
            tmpCat.setName("Snacks");
            tmpCat.setDescription("A small amount of food eaten between meals.");
            categoriesRepository.save(tmpCat);
            System.out.println(optional.get());
        }
        else
        {
            System.out.println("Category not found");
        }
    }

    private void findById() {
         Optional<Category> optional = categoriesRepository.findById(5);
         if (optional.isPresent()) {
             System.out.println(optional.get());
         }
         else
         {
             System.out.println("Category not found");
         }
    }

    private void save() {
        Category category = new Category();
        category.setName("Breakfast");
        category.setDescription("A light midday meal between breakfast and dinner.");
        categoriesRepository.save(category);
        System.out.println(category);
    }


}
