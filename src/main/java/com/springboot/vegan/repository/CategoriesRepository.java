package com.springboot.vegan.repository;

import com.springboot.vegan.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/*public interface CategoriesRepository extends CrudRepository<Category, Integer> {}*/
public interface CategoriesRepository extends JpaRepository<Category, Integer> {}
