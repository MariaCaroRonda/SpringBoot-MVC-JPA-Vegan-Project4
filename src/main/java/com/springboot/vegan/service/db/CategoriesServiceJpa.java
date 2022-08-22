package com.springboot.vegan.service.db;

import com.springboot.vegan.model.Category;
import com.springboot.vegan.repository.CategoriesRepository;
import com.springboot.vegan.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary // To allow the use of a second Service Class for testing
public class CategoriesServiceJpa implements ICategoriesService {

// Category methods implementation

    @Autowired
    private CategoriesRepository categoriesRepository;

    // Find all categories on the DB
    @Override
    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }

    // Find all categories on the DB and apply Pagination
    @Override
    public Page<Category> findAll(Pageable page) {
        return categoriesRepository.findAll(page);
    }

    @Override
    public void save(Category category) {
        categoriesRepository.save(category);
    }

    @Override
    public List<Category> findAllByName() {
        return categoriesRepository.findAll(Sort.by("name").ascending());
    }

    @Override
    public List<Category> findAllByNameDesc() {
        return categoriesRepository.findAll(Sort.by("name").descending());
    }

    @Override
    public List<Category> findAllByIdDesc() {
        return categoriesRepository.findAll(Sort.by("categoryId").descending());
    }

    @Override
    public List<Category> findAllByIdAsc() {
        return categoriesRepository.findAll(Sort.by("categoryId").ascending());
    }


    @Override
    public List<Category> findAllByIdDesc(Integer categoryId) {
        return categoriesRepository.findCategoriesByCategoryIdOrderByName(categoryId);

    }

    @Override
    public Category findById(Integer categoryId) {
        Optional<Category> optional = categoriesRepository.findById(categoryId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void delete(Integer categoryId) {
        categoriesRepository.deleteById(categoryId);
    }




    @Override
    public Page<Category> findAllPageAsc(Pageable page) {

        return (Page) categoriesRepository.findAll(page.getSort().ascending());

    }

    @Override
    public Page<Category> findSort(Pageable page) {
        return categoriesRepository.findAll(
        PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("name")));

    }

}
