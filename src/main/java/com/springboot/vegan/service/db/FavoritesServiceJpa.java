package com.springboot.vegan.service.db;

import com.springboot.vegan.model.Favorite;
import com.springboot.vegan.repository.FavoritesRepository;
import com.springboot.vegan.service.IFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import java.util.List;

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
    public List<Favorite> findAll() {
        return favoritesRepository.findAll();
    }

/*    @Override
    public List<Favorite> myFavorites(Integer userId) {
        return null;
    }*/


}
