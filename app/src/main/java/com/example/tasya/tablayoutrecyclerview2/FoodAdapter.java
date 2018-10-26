package com.example.tasya.tablayoutrecyclerview2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasya.tablayoutrecyclerview2.model.CategoriItems;
import com.squareup.picasso.Picasso;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    CategoriItems[] categoriItems;
    Context konteks;

    public FoodAdapter(CategoriItems[] categoriItems, Context konteks) {

        this.categoriItems = categoriItems;
        this.konteks = konteks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View s = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_list_item, parent, false);
        ViewHolder st = new ViewHolder(s);
        return st;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(categoriItems[position].getStrCategory());
        Picasso.get().load(categoriItems[position].getStrCategoryThumb()).into(holder.gbr);
        //holder.descr.setText(fooddrink.desc);
        //holder.time.setText(fooddrink.times);
        holder.imageV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                Toast.makeText(konteks, "" + categoriItems[position].getStrCategory(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (categoriItems != null)
            return categoriItems.length;
        return 0;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gbr;
        TextView title;
        //TextView descr;
        //TextView time;
        ImageView imageV;

        public ViewHolder(View itemView) {
            super(itemView);
            gbr = itemView.findViewById(R.id.foto);
            title = itemView.findViewById(R.id.judul);
            imageV = itemView.findViewById(R.id.imageView);
            //descr = itemView.findViewById(R.id.descf);
            //time = itemView.findViewById(R.id.timesf);
        }
    }
}
