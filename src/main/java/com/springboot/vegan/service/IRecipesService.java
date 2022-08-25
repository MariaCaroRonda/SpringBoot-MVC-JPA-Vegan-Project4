package com.springboot.vegan.service;

import com.springboot.vegan.model.Recipe;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRecipesService {

    void save(Recipe recipe);

    Recipe findById(Integer recipeId);


    List<Recipe> findAll();

    List<Recipe> findByExample(Example<Recipe> example);

    List<Recipe> findAllByNameAsc();

    List<Recipe> findAllByNameDesc();

    Page<Recipe> findAllExamplePage(Example<Recipe> example,
                                    Pageable pageable);




    void delete (Integer recipeId);

    List<Recipe> findFeaturedPremium();

    List<Recipe> findFeatured();


    List<Recipe> findByExampleSort(Example<Recipe> example);

    Page<Recipe> findAll(Pageable page);




}
