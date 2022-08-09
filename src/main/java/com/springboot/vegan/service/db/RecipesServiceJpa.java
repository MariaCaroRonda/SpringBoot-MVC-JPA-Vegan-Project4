package com.springboot.vegan.service.db;

import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.repository.RecipesRepository;
import com.springboot.vegan.service.IRecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary // To allow the use of another Service Class use for testing
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

    @Override
    public List<Recipe> findByExample(Example<Recipe> example) {
        return recipesRepository.findAll(example);
    }

    @Override
    public List<Recipe> findByExampleSort(Example<Recipe> example) {
        recipesRepository.findAll(example, Sort.by("name"));
        return null;
    }

    @Override
    public Page<Recipe> findAll(Pageable page) {
        return recipesRepository.findAll(page);
    }

    @Override
    public Page<Recipe> findAllExamplePage(Example<Recipe> example, Pageable pageable) {
        return recipesRepository.findAll(example, pageable);
    }

    @Override
    public List<Recipe> findAllByNameAsc() {
        return recipesRepository.findAll(Sort.by("name").ascending());
    }

    @Override
    public List<Recipe> findAllByNameDesc() {
        return recipesRepository.findAll(Sort.by("name").descending());
    }

/*    @Override
    public List<Recipe> findByName(String name) {
        List<Recipe> recipes = recipesRepository.findRecipesByNameLikeIgnoreCaseOrderByName(name);
        return recipes;
    }*/

/*    @Override
    public List<Recipe> findByName2(String name) {
        return recipesRepository.findRecipesByNameLike(name);
    }

    @Override
    public List<Recipe> findByIngredient(String ingredient) {
        return recipesRepository.findRecipesByIngredientsLike(ingredient);
    }*/
}
