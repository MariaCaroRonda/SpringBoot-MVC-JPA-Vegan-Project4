package com.springboot.vegan.service;

import com.springboot.vegan.model.Recipe;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/** This class is used only for testing the front end
 * without accessing the Database */
@Service
public class RecipesServiceImpl implements IRecipesService{

    private List<Recipe> recipesList = null;

    @Override
    public List<Recipe> findAll() {
        return recipesList;
    }

    @Override
    public Recipe findById(Integer recipeId) {

        for (Recipe rTmp : recipesList) {
            if (rTmp.getRecipeId() == recipeId) {
                return rTmp;
            }
        }
        return  null;
    }

    @Override
    public void save(Recipe recipe) {
        recipesList.add(recipe);
    }

    @Override
    public List<Recipe> findFeaturedPremium() {
        return null;
    }

    @Override
    public List<Recipe> findFeatured() {
        return null;
    }

    @Override
    public void delete(Integer recipeId) {

    }

    @Override
    public List<Recipe> findByExample(Example<Recipe> example) {
        return null;
    }

    @Override
    public Page<Recipe> findAll(Pageable page) {
        return null;
    }

/*    @Override
    public List<Recipe> findByName(String name) {
        return null;
    }

    @Override
    public List<Recipe> findByName2(String name) {
        return null;
    }

    @Override
    public List<Recipe> findByIngredient(String ingredient) {
        return null;
    }*/


    public RecipesServiceImpl() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        recipesList = new LinkedList<Recipe>();

        try {
            // Create Recipe 1
            Recipe recipe1 = new Recipe();
            recipe1.setRecipeId(1);
            recipe1.setName("Breakfast - Blueberry banana pancakes");
            recipe1.setIngredients("Flour, baking powder, sugar, plant-milk");
            recipe1.setCookingTime(25);
            recipe1.setDate(sdf.parse("08-02-2020"));
            recipe1.setFeatured(1);
            recipe1.setImageMeal("blueberry-banana-pancakes.png");


            // Create Recipe 2
            Recipe recipe2 = new Recipe();
            recipe2.setRecipeId(2);
            recipe2.setName("Lunch - Spinach Basil Pasta");
            recipe2.setIngredients("Pasta, basil, garlic, olive oil");
            recipe2.setCookingTime(20);
            recipe2.setDate(sdf.parse("18-03-2021"));
            recipe2.setFeatured(0);
            recipe2.setImageMeal("Lunch-Vegan Pasta2.png");

            // Create Recipe 3
            Recipe recipe3 = new Recipe();
            recipe3.setRecipeId(3);
            recipe3.setName("Dinner - Mushroom Potato Soup");
            recipe3.setIngredients("Wild mushrooms, spring onions, potatoes, red pepper,olive oil, water");
            recipe3.setCookingTime(30);
            recipe3.setDate(sdf.parse("23-01-2022"));
            recipe3.setFeatured(0);

            // Create Recipe 4
            Recipe recipe4 = new Recipe();
            recipe4.setRecipeId(4);
            recipe4.setName("Smoothies - Fruits Smoothie");
            recipe4.setIngredients("Carrots, Apple, Ginger, Pineapple and a cup of Almond milk");
            recipe4.setCookingTime(15);
            recipe4.setDate(sdf.parse("08-07-2019"));
            recipe4.setFeatured(1);
            recipe4.setImageMeal("Smoothies-mixed fruits smoothie2.png");

            recipesList.add(recipe1);
            recipesList.add(recipe2);
            recipesList.add(recipe3);
            recipesList.add(recipe4);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
