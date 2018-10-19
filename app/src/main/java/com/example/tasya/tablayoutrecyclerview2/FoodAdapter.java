package com.example.tasya.tablayoutrecyclerview2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<FoodDrink> foodList;

    public FoodAdapter(ArrayList<FoodDrink> foodList) {
        this.foodList = foodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View s = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_list_item, parent, false);
        ViewHolder st = new ViewHolder(s);
        return st;
    }

    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder, int position) {
        final FoodDrink fooddrink = foodList.get(position);
        holder.title.setText(fooddrink.title);
        holder.gbr.setBackground(fooddrink.gambar);
    }

    @Override
    public int getItemCount() {
        if (foodList != null)
            return foodList.size();
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView gbr;
        TextView title;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            gbr = itemView.findViewById(R.id.foto);
            title = itemView.findViewById(R.id.judul);
            cardview = itemView.findViewById(R.id.cardview2);
        }
    }
}
