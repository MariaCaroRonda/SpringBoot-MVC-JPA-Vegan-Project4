package com.springboot.vegan.model;

import java.util.Date;

public class Recipe {

    private Integer recipeId;
    private String recipeName;
    private String ingredients;
    private Date publicationDate;
    private Integer cookingTime;
    private Integer featured;

/*    private String imageMeal="no-image.png";
    private String instructions;
    private Integer recipePremium;
*/

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", publicationDate=" + publicationDate +
                ", cookingTime=" + cookingTime +
                '}';
    }
}
