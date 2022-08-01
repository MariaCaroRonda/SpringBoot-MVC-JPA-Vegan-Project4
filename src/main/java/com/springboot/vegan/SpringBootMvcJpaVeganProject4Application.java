package com.springboot.vegan;

import com.springboot.vegan.model.Category;
import com.springboot.vegan.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
            deleteAll();
            findByIds();
            findAll();
            existById();
            saveAll();
            findAllJpa();
            findAllSorting();
            findAllPaginating(); */

        findAllPaginatingSorting();

    }

    private void findAllPaginatingSorting() {
        // Start pagination of page 0 and size 3 (3 registries per page) and sorted by 'name'
        Page<Category> page = categoriesRepository.findAll(PageRequest.of(0, 4, Sort.by("name")));
        System.out.println("Total registries: " + page.getTotalElements());
        System.out.println("Total pages: " + page.getTotalPages());
        for (Category tmpCat : page.getContent()) {
            System.out.println(tmpCat.getCategoryId() + " " + tmpCat.getName());
        }

        page = categoriesRepository.findAll(PageRequest.of(0, 4, Sort.by("name").descending()));
        System.out.println("Total registries: " + page.getTotalElements());
        System.out.println("Total pages: " + page.getTotalPages());
        for (Category tmpCat : page.getContent()) {
            System.out.println(tmpCat.getCategoryId() + " " + tmpCat.getName());
        }
    }

    private void findAllPaginating() {
        // Start pagination of page 0 and size 3 (3 registries per page)
        Page<Category> page = categoriesRepository.findAll(PageRequest.of(0, 3));
        System.out.println("Total registries: " + page.getTotalElements());
        System.out.println("Total pages: " + page.getTotalPages());
        for (Category tmpCat : page.getContent()) {
            System.out.println(tmpCat.getCategoryId() + " " + tmpCat.getName());
        }
    }

    private void findAllSorting() {
        // Ascending order
        List<Category> categories = categoriesRepository.findAll(Sort.by("name"));
        displayList(categories);

        // Descending order
        categories = categoriesRepository.findAll(Sort.by("name").descending());
        displayList(categories);
    }

    private void saveAll() {
        List<Category> categories = getListCategories();
        categoriesRepository.saveAll(categories);
    }

    private void existById() {
        boolean exist = categoriesRepository.existsById(15);
        System.out.println("Does the category exist? " + exist);
    }

    private void findAll() {
        Iterable<Category> categories = categoriesRepository.findAll();
        for (Category tmpCat: categories) {
            System.out.println(tmpCat);
        }
    }

    private void findAllJpa() {
        // findAll() using JpaRepository
        List<Category> categories  = categoriesRepository.findAll();
        for (Category tmpCat : categories) {
            System.out.println(tmpCat.getCategoryId() + " " + tmpCat.getName());
        }
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
        /*categoriesRepository.deleteAll();*/
        // delete all using JpaRepository
        categoriesRepository.deleteAllInBatch();

    }

    private void countCategories() {
        long countCat = categoriesRepository.count();
        System.out.println("Total categories: " + countCat);
    }

    public void delete() {
        int categoryId = 2;
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


    private void displayList(List<Category> categories) {
        for (Category tmpCat : categories) {
            System.out.println(tmpCat.getCategoryId() + " " + tmpCat.getName());
        }
    }


    private List<Category> getListCategories () {

        List<Category> list = new LinkedList<Category>();
        Category category1, category2, category3, category4, category5;

        category1 = new Category();
        category1.setCategoryId((1));
        category1.setName("Breakfast");
        category1.setDescription("A meal eaten in the morning, the first meal of the day.");

        category2 = new Category();
        category2.setCategoryId((2));
        category2.setName("Lunch");
        category2.setDescription("A light midday meal between breakfast and dinner.");

        category3 = new Category();
        category3.setCategoryId((3));
        category3.setName("Dinner");
        category3.setDescription("The main meal of the day, usually eaten in the evening.");

        category4 = new Category();
        category4.setCategoryId((4));
        category4.setName("Snacks");
        category4.setDescription("A small amount of food eaten between meals.");

        category5 = new Category();
        category5.setCategoryId((5));
        category5.setName("Dessert");
        category5.setDescription("Usually a sweet course or dish served at the end of a meal.");

        Category category6 = new Category();
        category6.setCategoryId(6);
        category6.setName("Christmas Dinner");
        category6.setDescription("The main meal on Christmas day, eaten any time in the afternoon or evening.");

        Category category7 = new Category();
        category7.setCategoryId(7);
        category7.setName("Smoothies");
        category7.setDescription("A thick, smooth cold drink of fresh fruit / vegetables pureed with a plant-based drink/yogurt/water..");

        list.add(category1);
        list.add(category2);
        list.add(category3);
        list.add(category4);
        list.add(category5);
        list.add(category6);
        list.add(category7);

        return list;
    }


}
