package com.springboot.vegan.service;

import com.springboot.vegan.model.Profile;
import com.springboot.vegan.model.UserVegan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    Page<UserVegan> findAll(Pageable page);

    List<UserVegan> findByExample(Example<UserVegan> example);

    Page<UserVegan> findAllExamplePage(Example<UserVegan> example, Pageable pageable);


}
