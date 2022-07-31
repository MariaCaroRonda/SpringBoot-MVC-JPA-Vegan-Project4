package com.springboot.vegan.model;

import java.util.Date;

public class Recipe {

    private Integer recipeId;
    private String name;
    private String ingredients;
    private Date date;
    private Integer prepTime;
    private Integer cookingTime;
    private Integer featured=0;
    private String imageMeal="no-image.png";
    private Integer status;
    private String instructions;

    private Category category;

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Integer getFeatured() {
        return featured;
    }

    public String getImageMeal() {
        return imageMeal;
    }

    public void setImageMeal(String imageMeal) {
        this.imageMeal = imageMeal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public void setFeatured(Integer featured) {
        this.featured = featured;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", date=" + date +
                ", prepTime=" + prepTime +
                ", cookingTime=" + cookingTime +
                ", featured=" + featured +
                ", imageMeal='" + imageMeal + '\'' +
                ", status=" + status +
                ", instructions='" + instructions + '\'' +
                ", category=" + category +
                '}';
    }
}
