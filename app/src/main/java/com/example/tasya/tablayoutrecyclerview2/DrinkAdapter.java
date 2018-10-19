package com.example.tasya.tablayoutrecyclerview2;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {

    private List<FoodDrink> drinkList;
    Context konteks;

    public DrinkAdapter(ArrayList<FoodDrink> drinkList, Context konteks) {
        this.drinkList = drinkList;
        this.konteks = konteks;
    }

    @Override
    public DrinkAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View t = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drink_list_item, parent, false);
        DrinkAdapter.ViewHolder ts = new DrinkAdapter.ViewHolder(t);
        return ts;
    }

    @Override
    public void onBindViewHolder(DrinkAdapter.ViewHolder holder, int position) {
        final FoodDrink fooddrink = drinkList.get(position);
        holder.title.setText(fooddrink.title);
        holder.gbr.setBackground(fooddrink.gambar);
        holder.descr.setText(fooddrink.desc);
        holder.time.setText(fooddrink.times);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                Toast.makeText(konteks, "" + fooddrink.title, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView gbr;
        TextView title;
        TextView descr;
        TextView time;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            gbr = itemView.findViewById(R.id.gbr);
            title = itemView.findViewById(R.id.title);
            cardview = itemView.findViewById(R.id.cardview);
            descr = itemView.findViewById(R.id.desc);
            time = itemView.findViewById(R.id.times);
        }
    }

}
