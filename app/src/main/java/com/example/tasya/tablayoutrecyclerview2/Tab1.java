package com.example.tasya.tablayoutrecyclerview2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tasya.tablayoutrecyclerview2.model.CategoriItems;
import com.example.tasya.tablayoutrecyclerview2.model.CategoriesItems;
import com.example.tasya.tablayoutrecyclerview2.model.LatestItems;
import com.example.tasya.tablayoutrecyclerview2.model.MealsItems;
import com.example.tasya.tablayoutrecyclerview2.rest.ApiClient;
import com.example.tasya.tablayoutrecyclerview2.rest.CategoriesInterface;
import com.example.tasya.tablayoutrecyclerview2.rest.LatestInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab1 extends Fragment {

    ArrayList<FoodDrink> mList = new ArrayList<>();
    DrinkAdapter vAdapter;
    FoodAdapter mAdapter;
    RecyclerView recyclerView2, recyclerView3;

    CategoriesInterface categoriesInterface;
    Call<CategoriesItems> categoriesItemCall;
    CategoriItems[] categoriItems;

    LatestInterface latestInterface;
    Call<LatestItems> latestItemCall;
    MealsItems[] mealsItem;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab1, container, false);


        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView3 = view.findViewById(R.id.recyclerView3);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //recyclerView.setLayoutManager(layoutManager);
        //vAdapter = new DrinkAdapter(mList, getContext());
        //recyclerView.setAdapter(vAdapter);
        fillData();

        return view;

    }

    private void fillData() {

        categoriesInterface = ApiClient.getClient().create(CategoriesInterface.class);
        categoriesItemCall = categoriesInterface.getData();

        latestInterface = ApiClient.getClient().create(LatestInterface.class);
        latestItemCall = latestInterface.getData();

        categoriesItemCall.enqueue(new Callback<CategoriesItems>() {
            @Override
            public void onResponse(Call<CategoriesItems> call, Response<CategoriesItems> response) {
                categoriItems = response.body().getCategoriItems();

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView2.setLayoutManager(layoutManager);
                mAdapter = new FoodAdapter(categoriItems, getContext());
                recyclerView2.setAdapter(mAdapter);

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CategoriesItems> call, Throwable t) {
                Log.wtf("hasil", t.getMessage());
            }
        });

        latestItemCall.enqueue(new Callback<LatestItems>() {
            @Override
            public void onResponse(Call<LatestItems> call, Response<LatestItems> response) {
                mealsItem = response.body().getMealsItems();

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView3.setLayoutManager(layoutManager);
                vAdapter = new DrinkAdapter(mealsItem, getContext());
                recyclerView3.setAdapter(vAdapter);

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LatestItems> call, Throwable t) {
                Log.wtf("hasil", t.getMessage());
            }
        });

    }


}