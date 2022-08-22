package com.springboot.vegan.model;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@Entity
@Table(name = "Categories")  // mapping 'Category' model with 'Categories' table
public class Category {

    @Id  // Primary Key (PK) on 'Categories' table
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // PK Autoincrement
    private Integer categoryId;
    private String name;
    private String description;

    // Getters and Setters

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
