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

import java.util.List;

import okhttp3.internal.Util;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Meal> meals;
    private Context context;

    public Adapter(List<Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }

    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cards_layout, parent, false);
        return new MyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        Meal model = meals.get(position);
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(Utils.getRandomDrawbleColor());
//        requestOptions.error(Utils.getRandomDrawbleColor());
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);


        Glide.with(context)
                .load(model.getStrMealThumb())
//                .apply(requestOptions)

                .into(holder.imgFood);
        holder.nameFood.setText(model.getStrMeal());

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

        TextView nameFood, catFood;
        ImageView imgFood;
        OnItemClickListener onItemClickListener;
        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            nameFood = itemView.findViewById(R.id.tv_name);

            imgFood = itemView.findViewById(R.id.img_picfood);
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
