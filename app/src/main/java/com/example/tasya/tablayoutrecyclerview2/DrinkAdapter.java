package com.example.tasya.tablayoutrecyclerview2;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasya.tablayoutrecyclerview2.model.MealsItems;
import com.squareup.picasso.Picasso;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {

    MealsItems[] mealsItems;
    Context konteks;

    public DrinkAdapter(MealsItems[] mealsItems, Context konteks) {
        this.mealsItems = mealsItems;
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
    public void onBindViewHolder(DrinkAdapter.ViewHolder holder, final int position) {
        holder.title.setText(mealsItems[position].getStrMeal());
        //holder.gbr.setBackground(fooddrink.gambar);
        holder.category.setText(mealsItems[position].getStrCategory());
        holder.area.setText(mealsItems[position].getStrArea());
        Picasso.get().load(mealsItems[position].getStrMealThumb()).into(holder.gbr);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                Toast.makeText(konteks, "Instructions " + mealsItems[position].getStrInstruction(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mealsItems != null)
            return mealsItems.length;
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gbr;
        TextView title;
        TextView category;
        TextView area;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            gbr = itemView.findViewById(R.id.gbr);
            title = itemView.findViewById(R.id.title);
            cardview = itemView.findViewById(R.id.cardview);
            category = itemView.findViewById(R.id.categories);
            area = itemView.findViewById(R.id.areas);
        }
    }

}
