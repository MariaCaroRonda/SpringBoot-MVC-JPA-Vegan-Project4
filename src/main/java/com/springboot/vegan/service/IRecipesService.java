package com.springboot.vegan.service;

import com.springboot.vegan.model.Recipe;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRecipesService {

    List<Recipe> findAll();

    Recipe findById(Integer recipeId);

    void save(Recipe recipe);

    List<Recipe> findFeaturedPremium();

    List<Recipe> findFeatured();

    void delete (Integer recipeId);

    List<Recipe> findByExample(Example<Recipe> example);

    Page<Recipe> findAll(Pageable page);

    Page<Recipe> findAllExamplePage(Example<Recipe> example, Pageable pageable);

/*    List<Recipe> findByName(String name);

    List<Recipe> findByName2(String name);

    List<Recipe> findByIngredient(String ingredient);*/


}
