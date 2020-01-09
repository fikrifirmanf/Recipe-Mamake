package com.fikrifirmanf.recipemamake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.fikrifirmanf.recipemamake.api.ApiClient;
import com.fikrifirmanf.recipemamake.api.ApiInterface;
import com.fikrifirmanf.recipemamake.models.FoodBritish;
import com.fikrifirmanf.recipemamake.models.Meal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Meal> meals = new ArrayList<>();
    private Adapter adapter;
    private String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.my_recycler_view);
        //layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        LoadJson();



    }

    public void LoadJson(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<FoodBritish> call;
        call = apiInterface.getFoodBritish("British");
        call.enqueue(new Callback<FoodBritish>() {
            @Override
            public void onResponse(Call<FoodBritish> call, Response<FoodBritish> response) {
                if(response.isSuccessful() && response.body().getMeals() != null){

                    if(!meals.isEmpty()){
                        meals.clear();
                    }
                    meals = response.body().getMeals();
                    adapter = new Adapter(meals, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();



                }else {
                    Toast.makeText(MainActivity.this, "No Result!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FoodBritish> call, Throwable t) {

            }
        });
    }
}
