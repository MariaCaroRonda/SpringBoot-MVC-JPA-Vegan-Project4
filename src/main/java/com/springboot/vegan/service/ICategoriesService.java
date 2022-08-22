package com.springboot.vegan.service;

import com.springboot.vegan.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// Category methods interface
public interface ICategoriesService {

    List<Category> findAll();

    Page<Category> findAll(Pageable page);



    void save(Category category);

    List<Category> findAllByName();

    List<Category> findAllByNameDesc();

    List<Category> findAllByIdDesc();

    List<Category> findAllByIdAsc();

    List<Category> findAllByIdDesc(Integer categoryId);

    Category findById(Integer categoryId);

    void delete(Integer categoryId);

    Page<Category> findAllPageAsc(Pageable page);

    Page<Category> findSort(Pageable page);


}
