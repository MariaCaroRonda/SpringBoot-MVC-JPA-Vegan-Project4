package com.springboot.vegan.model;

import javax.persistence.*;

@Entity
@Table(name = "Profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment MySQL
    private Integer profileId;
    private String profile;

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", profile='" + profile + '\'' +
                '}';
    }
}
