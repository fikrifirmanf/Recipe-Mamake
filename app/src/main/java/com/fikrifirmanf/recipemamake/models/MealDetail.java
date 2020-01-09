package com.fikrifirmanf.recipemamake.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MealDetail {

    @SerializedName("meals")
    @Expose
    private List<MealRecipe> meals = null;

    public List<MealRecipe> getMeals() {
        return meals;
    }

    public void setMeals(List<MealRecipe> meals) {
        this.meals = meals;
    }

}