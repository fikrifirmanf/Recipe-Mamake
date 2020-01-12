package com.fikrifirmanf.recipemamake.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryModel {
    @SerializedName("meals")
    @Expose
    private List<CategoryMeal> meals = null;

    public List<CategoryMeal> getMeals() {
        return meals;
    }

    public void setMeals(List<CategoryMeal> meals) {
        this.meals = meals;
    }
}
