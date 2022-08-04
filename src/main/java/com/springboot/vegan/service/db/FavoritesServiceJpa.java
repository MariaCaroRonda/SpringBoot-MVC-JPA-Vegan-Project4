package com.springboot.vegan.service.db;

import com.springboot.vegan.model.Favorite;
import com.springboot.vegan.repository.FavoritesRepository;
import com.springboot.vegan.service.IFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Primary
public class FavoritesServiceJpa implements IFavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Override
    public void save(Favorite favorite) {
        favoritesRepository.save(favorite);
    }

    @Override
    public void delete(Integer favoriteId) {
        favoritesRepository.deleteById(favoriteId);
    }

    @Override
    public List<Favorite> findAll() {
        return favoritesRepository.findAll();
    }


    @Override
    public Favorite findById(Integer favoriteId) {
        Optional<Favorite> optional = favoritesRepository.findById(favoriteId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<Favorite> findAll(Pageable page) {
        return favoritesRepository.findAll(page);
    }


}
