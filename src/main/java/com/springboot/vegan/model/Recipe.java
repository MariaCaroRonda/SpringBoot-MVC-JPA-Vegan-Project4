package com.springboot.vegan.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Recipes") // mapping 'Recipe' model with 'Recipes' table
public class Recipe {

    @Id   // Primary Key (PK) on 'Recipes' table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment mySQL
    private Integer recipeId;
    private String name;            // Recipe name
    private String ingredients;     // Recipe ingredients
    private Date date;              // Publication date
    private Integer prepTime;       // Preparation time in minutes
    private Integer cookingTime;    // Cooking time in minutes
    private Integer featured=0;     // Feature recipe, default value = 0
    private String imageMeal="no-image.png"; // Picture of the meal already cooked
                                             // no image by default
    private String status; // status: {Premium (for registered users only), Normal}
    private String instructions;    // Instructions to prepare and cook a recipe

    // OneToOne relationship between 'Recipes' table and 'Categories' table
    @OneToOne // between 'Recipes' and 'Categories' using the column 'categoryId'
    @JoinColumn(name = "categoryId")
    private Category category;

    // Getters and Setters

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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


   // Remove the default values to avoid run time errors in HomeController
    public void reset() {
        this.imageMeal = null;
        this.featured = null;
    }


}
