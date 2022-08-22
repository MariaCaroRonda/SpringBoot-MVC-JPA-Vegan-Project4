package com.springboot.vegan.model;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="UsersVegan")  // mapping 'UserVegan' model with 'UsersVegan' table
public class UserVegan {

    @Id  // Primary Key (PK) on 'UsersVegan' table
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment MySQL
    private Integer userId;   // Unique identifier
    private String firstName; // user's first name
    private String lastName;  // user's last name
    private String email;     // email
    private String username; // used to log in to the website,
    // it doesn't have to match the user's firstName

    private String password;  // user's password
    private Integer status = 1; // {status: {not-locked = 1, locked = 0} (default value = 1)
    private Date registrationDate;

    // Configure ManyToMany relationship
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="UserVgProfile",
        joinColumns = @JoinColumn(name = "userId"),
        inverseJoinColumns = @JoinColumn(name = "profileId"))
    private List<Profile> profiles;

    // profiles: {Supervisor = 1, Administrator = 2,  UserVegan = 3} (default value = 3)
    public void add(Profile tmpProfile) {
        if (profiles == null) {
            profiles = new LinkedList<Profile>();
        }
        profiles.add(tmpProfile);
    }

    // Getters and Setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public void reset() {
        this.status = null;
    }

    @Override
    public String toString() {
        return "UserVegan{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
