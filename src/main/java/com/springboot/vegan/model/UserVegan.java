package com.springboot.vegan.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UsersVegan")
public class UserVegan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName; // user's first name
    private String lastName;
    private String username; // used to log in to the website, it doesn't have to match the user's firstName
    private String email;
    private String password;
    private Integer status; // {Administrator = 1, UserVegan = 2} default value = 2
    private Date registrationDate;

/*      @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "UserProfile",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "profileId"))
    private List<Profile> profileList;

    public void addProfile(Profile tempProfile) {
        if (profileList == null) {
            profileList = new LinkedList<Profile>();
        }
        profileList.add(tempProfile);
    }*/


/*    public List<Profile> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<Profile> profileList) {
        this.profileList = profileList;
    }*/

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "UserVegan{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
