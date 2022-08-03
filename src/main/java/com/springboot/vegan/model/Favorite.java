package com.springboot.vegan.model;

import javax.persistence.*;

@Entity
@Table(name = "Favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteId;
    private String comments;

    @OneToOne
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserVegan userVegan;

    public Favorite() {}

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
