package com.springboot.vegan.service.db;

import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.repository.RecipesRepository;
import com.springboot.vegan.service.IRecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class RecipesServiceJpa implements IRecipesService {

    @Autowired
    private RecipesRepository recipesRepository;

    @Override
    public List<Recipe> findAll() {
        return recipesRepository.findAll();
    }

    @Override
    public Recipe findById(Integer recipeId) {
        Optional<Recipe> optional = recipesRepository.findById(recipeId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void save(Recipe recipe) {
        recipesRepository.save(recipe);
    }


    @Override
    public List<Recipe> findFeaturedPremium() { // find featured and premium recipes
        return recipesRepository.findByFeaturedAndStatusOrderByRecipeIdDesc(1, "Premium");
    }

    @Override
    public List<Recipe> findFeatured() {
        int featured = 1;
        int notFeatured = 0;
        return recipesRepository.findByFeaturedOrderByName(featured);
    }

    @Override
    public void delete(Integer recipeId) {
        recipesRepository.deleteById(recipeId);
    }
}
