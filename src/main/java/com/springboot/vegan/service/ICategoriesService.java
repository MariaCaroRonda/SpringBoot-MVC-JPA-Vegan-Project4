package com.springboot.vegan.service;

import com.springboot.vegan.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoriesService {

    void save(Category category);

    List<Category> findAll();

    Category findById(Integer categoryId);

    void delete(Integer categoryId);

    Page<Category> findPagination(int pageNo, int pageSize,
                                  String sortField, String sortDirection);

}
