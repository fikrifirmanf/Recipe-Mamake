package com.fikrifirmanf.recipemamake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fikrifirmanf.recipemamake.models.CountryMeal;

import java.util.List;

public class CountryMealAdapter extends RecyclerView.Adapter<CountryMealAdapter.MyViewHolder> {
    private List<CountryMeal> meals;
    private Context context;
    int img [] = {
            R.drawable.american,
            R.drawable.british,
            R.drawable.canadian,
            R.drawable.chinese,
            R.drawable.dutch,
            R.drawable.egyptian,
            R.drawable.french,
            R.drawable.greek,
            R.drawable.indian,
            R.drawable.irish,
            R.drawable.italian,
            R.drawable.jamaican,
            R.drawable.japanese,
            R.drawable.kenyan,
            R.drawable.malaysian,
            R.drawable.mexican,
            R.drawable.moroccan,
            R.drawable.russian,
            R.drawable.spanish,
            R.drawable.thai,
            R.drawable.tunisian,
            R.drawable.turkish,
            R.drawable.unknown,
            R.drawable.vietnamese


    };

    public CountryMealAdapter(List<CountryMeal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }

    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout_country, parent, false);
        return new MyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        CountryMeal model = meals.get(position);


           holder.imgArea.setImageResource(img[position]);


        holder.mealArea.setText(model.getStrArea());

    }

    @Override
    public int getItemCount() {
        return 23;
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
            mealArea = itemView.findViewById(R.id.tv_meal_area);

            imgArea = itemView.findViewById(R.id.img_country);

            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
