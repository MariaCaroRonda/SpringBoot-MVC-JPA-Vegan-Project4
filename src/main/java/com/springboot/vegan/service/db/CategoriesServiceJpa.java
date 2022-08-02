package com.springboot.vegan.service.db;

import com.springboot.vegan.model.Category;
import com.springboot.vegan.repository.CategoriesRepository;
import com.springboot.vegan.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CategoriesServiceJpa implements ICategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public void save(Category category) {
        categoriesRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoriesRepository.findAll();
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

}
