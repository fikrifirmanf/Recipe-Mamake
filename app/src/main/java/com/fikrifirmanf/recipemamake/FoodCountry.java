package com.fikrifirmanf.recipemamake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fikrifirmanf.recipemamake.api.ApiClient;
import com.fikrifirmanf.recipemamake.api.ApiInterface;
import com.fikrifirmanf.recipemamake.models.CountryMeal;
import com.fikrifirmanf.recipemamake.models.CountryModel;
import com.fikrifirmanf.recipemamake.models.FoodBritish;
import com.fikrifirmanf.recipemamake.models.Meal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodCountry extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Meal> meals = new ArrayList<>();
    private Adapter adapter;
    private String area, cat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_food_country);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.my_recycler_view);



        Intent intent = getIntent();
        area = intent.getStringExtra("area");
        cat = intent.getStringExtra("cat");

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(cat != ""){

            LoadJsonCat();
            setTitle(cat);
        }
        if(area != ""){
            setTitle(area);
            LoadJson();
        }
    }

    private void initListener(){
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FoodCountry.this, MealRecipeDetail.class);
                Meal meal = meals.get(position);
                intent.putExtra("id", meal.getIdMeal());
                intent.putExtra("title", meal.getStrMeal());
                intent.putExtra("area", area);
                startActivity(intent);
            }
        });
    }

    public void LoadJson(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<FoodBritish> call;
        call = apiInterface.getFoodArea(area);
        call.enqueue(new Callback<FoodBritish>() {
            @Override
            public void onResponse(Call<FoodBritish> call, Response<FoodBritish> response) {
                if(response.isSuccessful() && response.body().getMeals() != null){

                    if(!meals.isEmpty()){
                        meals.clear();
                    }
                    meals = response.body().getMeals();
                    adapter = new Adapter(meals, FoodCountry.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(FoodCountry.this, "Data ada!", Toast.LENGTH_SHORT).show();
                    initListener();


                }else {
                    Toast.makeText(FoodCountry.this, "No Result!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FoodBritish> call, Throwable t) {

            }
        });
    }
    public void LoadJsonCat(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<FoodBritish> call;
        call = apiInterface.getFoodCat(cat);
        call.enqueue(new Callback<FoodBritish>() {
            @Override
            public void onResponse(Call<FoodBritish> call, Response<FoodBritish> response) {
                if(response.isSuccessful() && response.body().getMeals() != null){

                    if(!meals.isEmpty()){
                        meals.clear();
                    }
                    meals = response.body().getMeals();
                    adapter = new Adapter(meals, FoodCountry.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(FoodCountry.this, "Data ada!", Toast.LENGTH_SHORT).show();
                    initListener();


                }else {
                    Toast.makeText(FoodCountry.this, "No Result!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FoodBritish> call, Throwable t) {

            }
        });
    }

}
