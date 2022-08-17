package com.springboot.vegan;

import com.springboot.vegan.model.Category;
import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootMvcJpaVeganProject4ApplicationTests{


    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private UsersVeganRepository usersVeganRepository;

    @Autowired
    private ProfilesRepository profilesRepository;

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Test
    void contextLoads() {
    }

    @Test
    private void displayListRecipe(List<Recipe> recipes) {
        for (Recipe tmpRecipe : recipes) {
            System.out.println(tmpRecipe.getRecipeId() + " " + tmpRecipe.getName());
        }
    }

    @Test
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
