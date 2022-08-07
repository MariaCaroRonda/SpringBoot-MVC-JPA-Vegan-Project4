package com.springboot.vegan.repository;

import com.springboot.vegan.model.Profile;
import com.springboot.vegan.model.UserVegan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersVeganRepository extends JpaRepository<UserVegan, Integer> {

    UserVegan findUserVeganByUsername (String username);

    Boolean existsUserVeganByUsername (String username);

    Boolean existsUserVeganByEmail (String email);

    List<Profile> findUserVeganByProfiles (Integer userId);


}
