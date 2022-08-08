package com.springboot.vegan.service.db;

import com.springboot.vegan.model.Profile;
import com.springboot.vegan.model.Recipe;
import com.springboot.vegan.model.UserVegan;
import com.springboot.vegan.repository.UsersVeganRepository;
import com.springboot.vegan.service.IUsersVgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public UserVegan findById(Integer userId) {
        Optional<UserVegan> optional = usersVeganRepository.findById(userId);
        UserVegan userVegan = optional.get();
        return userVegan;
    }

    @Override
    public boolean existUsername(String username) {
        boolean existUsername = usersVeganRepository.existsUserVeganByUsername(username);
        return existUsername;
    }

    @Override
    public boolean existUserEmail(String email) {
        boolean existUserEmail = usersVeganRepository.existsUserVeganByEmail(email);
        return existUserEmail;
    }

    @Override
    public List<Profile> findProfilesUser(Integer userId) {
        Optional<UserVegan> optional = usersVeganRepository.findById(userId);
        List<Profile> profiles = optional.get().getProfiles();
        //List<Profile> profiles2 = usersVeganRepository.findUserVeganByProfiles(userId);
        return profiles;
    }

    @Override
    public Page<UserVegan> findAllPaginate() {
        Pageable pageable = PageRequest.of(0, 5);
        return usersVeganRepository.findAll(pageable);
    }


}
