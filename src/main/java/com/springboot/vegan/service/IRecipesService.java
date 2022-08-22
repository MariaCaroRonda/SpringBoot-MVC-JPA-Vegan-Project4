package com.springboot.vegan.service;

import com.springboot.vegan.model.Recipe;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRecipesService {

    List<Recipe> findAll();

    List<Recipe> findByExample(Example<Recipe> example);

    List<Recipe> findAllByNameAsc();

    List<Recipe> findAllByNameDesc();

    Page<Recipe> findAllExamplePage(Example<Recipe> example,
                                    Pageable pageable);



    Recipe findById(Integer recipeId);

    void save(Recipe recipe);

    List<Recipe> findFeaturedPremium();

    List<Recipe> findFeatured();

    void delete (Integer recipeId);


    List<Recipe> findByExampleSort(Example<Recipe> example);

    Page<Recipe> findAll(Pageable page);




}
