package com.example.tasya.tablayoutrecyclerview2;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Tab1 extends Fragment {

    ArrayList<FoodDrink> mList = new ArrayList<>();
    DrinkAdapter vAdapter;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab1, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        vAdapter = new DrinkAdapter(mList, getContext());
        recyclerView.setAdapter(vAdapter);
        fillData();

        return view;

    }

    private void fillData() {
        Resources resources = getResources();
        String[] arJudul = resources.getStringArray(R.array.drinks);
        TypedArray a = resources.obtainTypedArray(R.array.drink_picture);
        Drawable[] arFoto = new Drawable[a.length()];
        for (int i = 0; i < arFoto.length; i++) {
            arFoto[i] = a.getDrawable(i);
        }
        a.recycle();
        for (int i = 0; i < arJudul.length; i++) {
            mList.add(new FoodDrink(arJudul[i], arFoto[i]));
        }
        vAdapter.notifyDataSetChanged();
    }

}