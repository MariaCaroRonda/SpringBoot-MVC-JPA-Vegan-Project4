package com.springboot.vegan.repository;

import com.springboot.vegan.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<Profile, Integer> {
}
