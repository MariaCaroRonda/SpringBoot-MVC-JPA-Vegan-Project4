package com.springboot.vegan.repository;

import com.springboot.vegan.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/*public interface CategoriesRepository extends CrudRepository<Category, Integer> {}*/
public interface CategoriesRepository extends JpaRepository<Category, Integer> {

    List<Category> findCategoriesByCategoryIdOrderByName(Integer categoryId);

    List<Category> findCategoriesByCategoryIdOrderByNameDesc(Integer categoryId);

 //   Page<Category> findAllByCategoryIdOrderByCategoryId(Pageable page);



}
