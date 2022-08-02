package com.springboot.vegan.service;

import com.springboot.vegan.model.Recipe;

import java.util.List;

public interface IRecipesService {

    List<Recipe> findAll();

    Recipe findById(Integer recipeId);

    void save(Recipe recipe);

    List<Recipe> findFeatured();

    void delete (Integer recipeId);
}
