package com.springboot.vegan.repository;

import com.springboot.vegan.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorite, Integer> {


}
