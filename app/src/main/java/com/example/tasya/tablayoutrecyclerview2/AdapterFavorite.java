package com.example.tasya.tablayoutrecyclerview2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasya.tablayoutrecyclerview2.model.FavoriteModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolder> {
    private Context context;
    private List<FavoriteModel> favoriteModels;

    public AdapterFavorite(Context context, List<FavoriteModel> favoriteModels) {
        this.context = context;
        this.favoriteModels = favoriteModels;
    }

    @NonNull
    @Override
    public AdapterFavorite.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.drink_list_item, viewGroup, false);
        return new AdapterFavorite.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final FavoriteModel mealsItems = favoriteModels.get(position);
        viewHolder.title.setText(mealsItems.getNama());
        //holder.gbr.setBackground(fooddrink.gambar);
        viewHolder.category.setText(mealsItems.getKategori());
        viewHolder.area.setText(mealsItems.getJenis());
        Picasso.get().load(mealsItems.getGambar()).into(viewHolder.gbr);

        viewHolder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                Toast.makeText(context, "" + mealsItems.getNama(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gbr;
        TextView title;
        TextView category;
        TextView area;
        CardView cardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gbr = itemView.findViewById(R.id.gbr2);
            title = itemView.findViewById(R.id.title2);
            cardview = itemView.findViewById(R.id.cardview);
            category = itemView.findViewById(R.id.categories2);
            area = itemView.findViewById(R.id.areas2);
        }
    }
}

