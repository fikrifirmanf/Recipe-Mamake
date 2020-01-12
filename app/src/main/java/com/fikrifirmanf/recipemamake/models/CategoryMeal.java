package com.fikrifirmanf.recipemamake.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class CategoryMeal {
    @SerializedName("strCategory")
    @Expose
    private String strCategory;

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }
}
