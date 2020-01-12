package com.fikrifirmanf.recipemamake.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryModel {

    @SerializedName("meals")
    @Expose
    private List<CountryMeal> meals = null;

    public List<CountryMeal> getMeals() {
        return meals;
    }

    public void setMeals(List<CountryMeal> meals) {
        this.meals = meals;
    }

}