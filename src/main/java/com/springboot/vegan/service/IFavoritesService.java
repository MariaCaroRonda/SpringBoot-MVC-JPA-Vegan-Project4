package com.springboot.vegan.service;

import com.springboot.vegan.model.Favorite;
import com.springboot.vegan.model.UserVegan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFavoritesService {

    void save(Favorite favorite);

    void delete(Integer favoriteId);

    List<Favorite> findAll();

    Favorite findById(Integer favoriteId);

    Page<Favorite> findAll(Pageable page);

    List<Favorite> findByUser(UserVegan user);

    boolean isRecipePresent(Integer recipeId);

    boolean isRecipePresentUserFavorite(Integer recipeId, Integer userId);
}
