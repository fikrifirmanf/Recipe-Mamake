package com.fikrifirmanf.recipemamake;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.fikrifirmanf.recipemamake.models.Meal;
import com.fikrifirmanf.recipemamake.models.MealRecipe;

import java.util.List;


public class AdapterMealDetail extends RecyclerView.Adapter<AdapterMealDetail.MyViewHolder> {
    private List<MealRecipe> meals;
    private Context context;

    public AdapterMealDetail(List<MealRecipe> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_meal_recipe_detail, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        MealRecipe model = meals.get(position);
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(Utils.getRandomDrawbleColor());
//        requestOptions.error(Utils.getRandomDrawbleColor());
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);


        Glide.with(context)
                .load(model.getStrMealThumb())
//                .apply(requestOptions)

                .into(holder.imgFood);
        holder.nameFood.setText(model.getStrMeal());
        holder.catFood.setText(model.getStrCategory());
        holder.descFood.setText(model.getStrInstructions());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameFood, catFood, descFood;
        ImageView imgFood;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameFood = itemView.findViewById(R.id.rv_name);
            catFood = itemView.findViewById(R.id.rv_cat);
            imgFood = itemView.findViewById(R.id.rv_pics);
            descFood = itemView.findViewById(R.id.rv_desc);

        }


    }
}
