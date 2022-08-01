package com.springboot.vegan.repository;

import com.springboot.vegan.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipesRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByStatus(String status);


}
