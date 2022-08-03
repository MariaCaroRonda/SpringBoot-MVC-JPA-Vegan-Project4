package com.springboot.vegan.service;

import com.springboot.vegan.model.Favorite;

import java.util.List;

public interface IFavoritesService {

    void save(Favorite favorite);

    List<Favorite> findAll();

    //List<Favorite> myFavorites(Integer userId);
}
