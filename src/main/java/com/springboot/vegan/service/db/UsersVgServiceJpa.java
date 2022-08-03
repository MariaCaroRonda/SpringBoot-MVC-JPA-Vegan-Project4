package com.springboot.vegan.service.db;

import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.repository.UsersVeganRepository;
import com.springboot.vegan.service.IUsersVgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UsersVgServiceJpa implements IUsersVgService {

    @Autowired
    private UsersVeganRepository usersVeganRepository;

    @Override
    public void save(UserVegan userVegan) {
        usersVeganRepository.save(userVegan);
    }

    @Override
    public void delete(Integer userId) {
        usersVeganRepository.deleteById(userId);
    }

    @Override
    public List<UserVegan> findAll() {
        return usersVeganRepository.findAll();
    }

    @Override
    public UserVegan findByUsername(String username) {

        return usersVeganRepository.findUserVeganByUsername(username);
    }


}
