package com.springboot.vegan.model;

import javax.persistence.*;

@Entity
@Table(name = "Favorites")  // mapping 'Favorite' model with 'Favorites' table
public class Favorite {

    @Id  // Primary key on 'Favorites' table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteId;
    private String comments = "comments";

    // Configure the OneToOne Relationship between 'Favorites' and 'Recipes' tables
    @OneToOne
    @JoinColumn(name = "recipeId") // foreign key in Recipes table
    private Recipe recipe;

    // Configure the OneToOne Relationship between 'Favorites' and 'UsersVegan' tables
    @OneToOne
    @JoinColumn(name = "userId")  // foreign key in UsersVegan table
    private UserVegan userVegan;

    public Favorite() {}

    // Getters and Setters

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UserVegan getUserVegan() {
        return userVegan;
    }

    public void setUserVegan(UserVegan userVegan) {
        this.userVegan = userVegan;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "favoriteId=" + favoriteId +
                ", comments='" + comments + '\'' +
                ", recipe=" + recipe +
                ", userVegan=" + userVegan +
                '}';
    }

}
