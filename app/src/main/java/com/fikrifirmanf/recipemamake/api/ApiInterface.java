package com.fikrifirmanf.recipemamake.api;

import com.fikrifirmanf.recipemamake.models.CountryModel;
import com.fikrifirmanf.recipemamake.models.FoodBritish;
import com.fikrifirmanf.recipemamake.models.MealDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("filter.php")
    Call<FoodBritish> getFoodArea(
            @Query("a") String country
    );
    @GET("lookup.php")
    Call<MealDetail> getMealDetail(
            @Query("i") String id
    );
    @GET("list.php")
    Call<CountryModel> getCountryMeal(
            @Query("a") String namaCountry
    );
//    @GET("list.php")
//    Call<CountryModel> getCategoryMeal(
//            @Query("c") String namaCategor
//    );

}
