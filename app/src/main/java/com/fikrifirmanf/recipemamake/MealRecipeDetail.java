package com.fikrifirmanf.recipemamake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.fikrifirmanf.recipemamake.api.ApiClient;
import com.fikrifirmanf.recipemamake.api.ApiInterface;
import com.fikrifirmanf.recipemamake.models.FoodBritish;
import com.fikrifirmanf.recipemamake.models.Meal;
import com.fikrifirmanf.recipemamake.models.MealDetail;
import com.fikrifirmanf.recipemamake.models.MealRecipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealRecipeDetail extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<MealRecipe> meals = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    private String TAG = MainActivity.class.getSimpleName();

    private String mealId, title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_recipe_detail);


        recyclerView = findViewById(R.id.rv_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(MealRecipeDetail.this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        mealId = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        setTitle(title);
        LoadJson();



    }

//    private void initListener(){
//        adapters.setOnItemClickListener(new Adapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(MainActivity.this, MealRecipeDetail.class);
//                Meal meal = meals.get(position);
//                intent.putExtra("id", meal.getIdMeal());
//                startActivity(intent);
//            }
//        });
//    }

    public void LoadJson(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<MealDetail> call;
        call = apiInterface.getMealDetail(mealId);
        call.enqueue(new Callback<MealDetail>() {
            @Override
            public void onResponse(Call<MealDetail> call, Response<MealDetail> response) {
                if(response.isSuccessful() && response.body().getMeals() != null){

                    if(!meals.isEmpty()){
                        meals.clear();
                    }
                    meals = response.body().getMeals();

                    adapter = new FoodDetailAdapter(meals, MealRecipeDetail.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    //initListener();


                }else {
                    Toast.makeText(MealRecipeDetail.this, "No Result!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MealDetail> call, Throwable t) {

            }
        });
    }
}
