package com.fikrifirmanf.recipemamake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fikrifirmanf.recipemamake.api.ApiClient;
import com.fikrifirmanf.recipemamake.api.ApiInterface;
import com.fikrifirmanf.recipemamake.models.CategoryMeal;
import com.fikrifirmanf.recipemamake.models.CategoryModel;
import com.fikrifirmanf.recipemamake.models.CountryMeal;
import com.fikrifirmanf.recipemamake.models.CountryModel;
import com.fikrifirmanf.recipemamake.models.FoodBritish;
import com.fikrifirmanf.recipemamake.models.Meal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewcountry, recyclerViewCategory;
    private RecyclerView.LayoutManager layoutManager;

    private List<CountryMeal> countryMeals = new ArrayList<>();
    private List<CategoryMeal> categoryMeals = new ArrayList<>();

    private CountryMealAdapter countryMealAdapter;
    private CategoryAdapter categoryAdapter;

    private String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewcountry = findViewById(R.id.my_recycler_viewcat);
        recyclerViewCategory = findViewById(R.id.my_recycler_viewcatfood);

        //layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);

        LoadJsonCountry();
        LoadJsonCategory();


    }

    private void initListener() {
        countryMealAdapter.setOnItemClickListener(new CountryMealAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, FoodCountry.class);
                CountryMeal meal = countryMeals.get(position);
                intent.putExtra("area", meal.getStrArea());

                startActivity(intent);
            }
        });
    }
    private void initListenerCat() {
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, FoodCountry.class);
                CategoryMeal meal = categoryMeals.get(position);
                intent.putExtra("cat", meal.getStrCategory());

                startActivity(intent);
            }
        });
    }


    public void LoadJsonCountry() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CountryModel> call;
        call = apiInterface.getCountryMeal("list");
        call.enqueue(new Callback<CountryModel>() {
            @Override
            public void onResponse(Call<CountryModel> call, Response<CountryModel> response) {
                if (response.isSuccessful() && response.body().getMeals() != null) {

                    if (!countryMeals.isEmpty()) {
                        countryMeals.clear();
                    }
                    countryMeals = response.body().getMeals();
                    countryMealAdapter = new CountryMealAdapter(countryMeals, MainActivity.this);
                    recyclerViewcountry.setAdapter(countryMealAdapter);
                    countryMealAdapter.notifyDataSetChanged();
                    recyclerViewcountry.setLayoutManager(new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.HORIZONTAL, false));
                    recyclerViewcountry.setItemAnimator(new DefaultItemAnimator());
                    initListener();


                } else {
                    Toast.makeText(MainActivity.this, "No Result!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CountryModel> call, Throwable t) {

            }
        });
    }
    public void LoadJsonCategory() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CategoryModel> call;
        call = apiInterface.getCategoryMeal("list");
        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                if (response.isSuccessful() && response.body().getMeals() != null) {

                    if (!categoryMeals.isEmpty()) {
                        categoryMeals.clear();
                    }
                    categoryMeals = response.body().getMeals();
                    categoryAdapter = new CategoryAdapter(categoryMeals, MainActivity.this);
                    recyclerViewCategory.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                    recyclerViewCategory.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
                    recyclerViewCategory.setItemAnimator(new DefaultItemAnimator());
                    recyclerViewCategory.setNestedScrollingEnabled(false);
                    initListenerCat();


                } else {
                    Toast.makeText(MainActivity.this, "No Result!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(this, About.class));
        }
        return true;
    }
}

