package com.fikrifirmanf.recipemamake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fikrifirmanf.recipemamake.models.CategoryMeal;


import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private List<CategoryMeal> meals;
    private Context context;
    int img [] = {
            R.drawable.beef,
            R.drawable.breakfest,
            R.drawable.chicken,
            R.drawable.dessert,
            R.drawable.goat,
            R.drawable.lamb,
            R.drawable.misc,
            R.drawable.wvqpwt1468339226,
            R.drawable.pork,
            R.drawable.seafood,
            R.drawable.side,
            R.drawable.starter,
            R.drawable.vegan,
            R.drawable.vegetarian


    };

    public CategoryAdapter(List<CategoryMeal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }

    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout_category, parent, false);
        return new MyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        CategoryMeal model = meals.get(position);


        holder.imgArea.setImageResource(img[position]);


        holder.mealArea.setText(model.getStrCategory());

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mealArea;
        ImageView imgArea;
        OnItemClickListener onItemClickListener;
        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mealArea = itemView.findViewById(R.id.tv_meal_category);

            imgArea = itemView.findViewById(R.id.img_category);

            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
