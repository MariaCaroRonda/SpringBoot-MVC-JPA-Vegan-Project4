package com.springboot.vegan.service;

import com.springboot.vegan.model.UserVegan;

import java.util.List;

public interface IUsersVgService {

    void save (UserVegan userVegan);

    void delete(Integer userId);

    List<UserVegan> findAll();

    UserVegan findByUsername(String username);


}
