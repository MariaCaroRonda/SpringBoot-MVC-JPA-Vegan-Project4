package com.springboot.vegan.service;

import com.springboot.vegan.model.Profile;
import com.springboot.vegan.model.UserVegan;
import org.apache.catalina.User;

import java.util.List;

public interface IUsersVgService {

    void save (UserVegan userVegan);

    void delete(Integer userId);

    List<UserVegan> findAll();

    UserVegan findByUsername(String username);

    UserVegan findById(Integer userId);

    boolean existUsername (String username);

    boolean existUserEmail (String email);

    List<Profile> findProfilesUser(Integer userId);


}
