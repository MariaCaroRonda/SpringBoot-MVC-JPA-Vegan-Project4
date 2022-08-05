package com.springboot.vegan.repository;

import com.springboot.vegan.model.Favorite;
import com.springboot.vegan.model.UserVegan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorite, Integer> {

   // List<Favorite> findAllByIdAndUserVeganUserId(List<Favorite> favorites, Integer userId);

    List<Favorite> findFavoritesByUserVeganEquals(UserVegan userVegan);

//    Boolean existsFavoriteByRecipeId(Integer recipeId);

    boolean existsByRecipeRecipeId (Integer integer);

    boolean existsByRecipeRecipeIdAndUserVegan_UserId (Integer recipeId, Integer userID);
}
