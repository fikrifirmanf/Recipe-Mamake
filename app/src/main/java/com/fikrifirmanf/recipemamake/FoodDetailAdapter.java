package com.fikrifirmanf.recipemamake;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
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

import okhttp3.internal.Util;

public class FoodDetailAdapter extends RecyclerView.Adapter<FoodDetailAdapter.MyViewHolder> {
    private List<MealRecipe> meals;
    private Context context;

    public FoodDetailAdapter(List<MealRecipe> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail, parent, false);
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
//        Bahane
        if(!model.getStrIngredient1().isEmpty()){
            holder.bahane.append("\n \u2022"+model.getStrIngredient1());
        }
        if(!model.getStrIngredient2().isEmpty()){
            holder.bahane.append("\n \u2022"+model.getStrIngredient2());
        }
        if(!model.getStrIngredient3().isEmpty()){
            holder.bahane.append("\n \u2022"+model.getStrIngredient3());
        }
        if(!model.getStrIngredient4().isEmpty()){
            holder.bahane.append("\n \u2022"+model.getStrIngredient4());
        }
        if(!model.getStrIngredient5().isEmpty()){
            holder.bahane.append("\n \u2022"+model.getStrIngredient5());
        }

//        Measurenya
        if(!model.getStrMeasure1().isEmpty() && !Character.isWhitespace(model.getStrMeasure1().charAt(0))){
            holder.totalBahan.append("\n : "+model.getStrMeasure1());
        }
        if(!model.getStrMeasure2().isEmpty() && !Character.isWhitespace(model.getStrMeasure2().charAt(0))){
            holder.totalBahan.append("\n : "+model.getStrMeasure2());
        }
        if(!model.getStrMeasure3().isEmpty() && !Character.isWhitespace(model.getStrMeasure3().charAt(0))){
            holder.totalBahan.append("\n : "+model.getStrMeasure3());
        }
        if(!model.getStrMeasure4().isEmpty() && !Character.isWhitespace(model.getStrMeasure4().charAt(0))){
            holder.totalBahan.append("\n : "+model.getStrMeasure4());
        }


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameFood, catFood, descFood, bahane, totalBahan;
        ImageView imgFood;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            nameFood = itemView.findViewById(R.id.tv_item_name);
            catFood =itemView.findViewById(R.id.tv_item_cat);
            descFood = itemView.findViewById(R.id.tv_item_desc);
            imgFood = itemView.findViewById(R.id.img_item_photo);
            bahane = itemView.findViewById(R.id.tv_item_bahane);
            totalBahan = itemView.findViewById(R.id.tv_item_bahan_total);


        }


    }
}
