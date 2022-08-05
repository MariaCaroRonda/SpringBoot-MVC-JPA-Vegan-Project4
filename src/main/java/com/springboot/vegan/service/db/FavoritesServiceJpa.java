package com.springboot.vegan.service.db;

import com.springboot.vegan.model.Favorite;
import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.repository.FavoritesRepository;
import com.springboot.vegan.repository.RecipesRepository;
import com.springboot.vegan.service.IFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Primary
public class FavoritesServiceJpa implements IFavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private RecipesRepository recipesRepository;

    @Override
    public void save(Favorite favorite) {
        favoritesRepository.save(favorite);
    }

    @Override
    public void delete(Integer favoriteId) {
        favoritesRepository.deleteById(favoriteId);
    }

    @Override
    public List<Favorite> findAll() {
        return favoritesRepository.findAll();
    }


    @Override
    public Favorite findById(Integer favoriteId) {
        Optional<Favorite> optional = favoritesRepository.findById(favoriteId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<Favorite> findAll(Pageable page) {
        return favoritesRepository.findAll(page);
    }


    @Override
    public List<Favorite> findByUser(UserVegan user) {
        return favoritesRepository.findFavoritesByUserVeganEquals(user);
    }

    @Override
    public boolean isRecipePresent(Integer recipeId) {
        boolean isPresent = favoritesRepository.existsByRecipeRecipeId(recipeId);
        return isPresent;


    }


    @Override
    public boolean isRecipePresentUserFavorite(Integer recipeId, Integer userId) {
        boolean isPresent = favoritesRepository
                .existsByRecipeRecipeIdAndUserVegan_UserId(recipeId, userId);
        return isPresent;
    }


}
