package com.springboot.vegan.service.db;

import com.springboot.vegan.model.Profile;
import com.springboot.vegan.repository.ProfilesRepository;
import com.springboot.vegan.service.IProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProfilesServiceJpa implements IProfilesService {

    @Autowired
    private ProfilesRepository profilesRepository;


/*    @Override
    public Profile findProfile(Integer userId) {
        *//*List<Profile> profiles = profilesRepository.findProfileByUserVegan_UserId(userId);*//*
        return null;
    }*/

 /*    @Override
   public List<Profile> findProfileUser(Integer userId) {
        List<Profile> profiles = profilesRepository.findProfileByUserVegan_UserId(userId);

        return profiles;
    }*/
}
