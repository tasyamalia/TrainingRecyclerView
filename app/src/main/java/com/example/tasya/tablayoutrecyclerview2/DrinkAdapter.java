package com.example.tasya.tablayoutrecyclerview2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tasya.tablayoutrecyclerview2.model.FavoriteModel;
import com.example.tasya.tablayoutrecyclerview2.model.MealsItems;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {

    MealsItems[] mealsItems;
    Context konteks;
    Realm realm;
    RealmHelper realmHelper;
    FavoriteModel favoriteModel;

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

        //Set up Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
//                favoriteModel = new FavoriteModel();
//                favoriteModel.setNama(mealsItems[position].getStrMeal());
//                favoriteModel.setKategori(mealsItems[position].getStrCategory());
//                favoriteModel.setGambar(mealsItems[position].getStrMealThumb());
//                favoriteModel.setJenis(mealsItems[position].getStrArea());
//
//                realmHelper = new RealmHelper(realm);
//                realmHelper.save(favoriteModel);
//
//                Toast.makeText(konteks, "data tambah", Toast.LENGTH_SHORT).show();
                openDtl(mealsItems[position].getStrCategory(), mealsItems[position].getStrArea(), mealsItems[position].getStrMealThumb(), mealsItems[position].getStrInstruction());

            }
        });
    }

    private void openDtl(String title, String category, String MealThumb, String instruction) {
        Intent dtl = new Intent(konteks, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Title", title);
        bundle.putString("Category", category);
        bundle.putString("MealThumb", MealThumb);
        bundle.putInt("Id", 1);
        bundle.putString("Instruction", instruction);
        dtl.putExtras(bundle);
        konteks.startActivity(dtl);
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
            gbr = itemView.findViewById(R.id.gbr2);
            title = itemView.findViewById(R.id.title2);
            cardview = itemView.findViewById(R.id.cardview);
            category = itemView.findViewById(R.id.categories2);
            area = itemView.findViewById(R.id.areas2);
        }
    }

}
