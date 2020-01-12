package com.fikrifirmanf.recipemamake.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryMeal {

    @SerializedName("strArea")
    @Expose
    private String strArea;

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

}