package com.springboot.vegan.repository;

import com.springboot.vegan.model.UserVegan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersVeganRepository extends JpaRepository<UserVegan, Integer> {

    UserVegan findUserVeganByUsername (String username);


}
