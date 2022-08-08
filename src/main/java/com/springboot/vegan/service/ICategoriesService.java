package com.springboot.vegan.service;

import com.springboot.vegan.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoriesService {

    void save(Category category);

    List<Category> findAll();

    List<Category> findAllByName();

    List<Category> findAllByNameDesc();

    List<Category> findAllByIdDesc();

    List<Category> findAllByIdAsc();

    List<Category> findAllByIdDesc(Integer categoryId);

    Category findById(Integer categoryId);

    void delete(Integer categoryId);

    Page<Category> findAll(Pageable page);



}
