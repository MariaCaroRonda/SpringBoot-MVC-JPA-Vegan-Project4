package com.springboot.vegan.repository;

import com.springboot.vegan.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipesRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByStatus(String status);

    // Example of wrong Query Method (ById -> not found because 'Id' does not exist).
    // The correct query must include ByRecipeId instead ('recipeId' does exist in 'Recipe' class).
    //List<Recipe> findByFeaturedAndStatusOrderByIdDesc(int featured, String status);

    List<Recipe> findByFeaturedAndStatusOrderByRecipeIdDesc (int featured, String status);

    List<Recipe> findByCookingTimeBetween(int time1, int time2);

    List<Recipe> findByCookingTimeBetweenOrderByCookingTime(int time1, int time2);

    List<Recipe> findByCookingTimeBetweenOrderByCookingTimeDesc(int time1, int time2);

    List<Recipe> findByStatusIn(String[] status);

    List<Recipe> findByFeaturedOrderByName(int featured);

    /*List<Recipe> findRecipeByNameLikeIgnoreCaseOrderByName(String ingredient);*/
/*
    List<Recipe> findRecipesByNameLikeIgnoreCaseOrderByName(String name);

    List<Recipe> findRecipesByNameLike(String name);

    List<Recipe> findRecipesByIngredientsLike(String ingredient);

    List<Recipe> findRecipesByNameContains (String name);

    //List<Recipe> findRecipesByNameIn(String string);
    List<Recipe> findRecipesByIngredientsContaining(String ingredient);

    List<Recipe> findRecipesByNameOrder(String recipe);*/


}
