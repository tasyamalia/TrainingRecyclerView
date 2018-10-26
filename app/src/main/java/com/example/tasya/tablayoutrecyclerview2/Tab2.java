package com.example.tasya.tablayoutrecyclerview2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tasya.tablayoutrecyclerview2.model.LatestItems;
import com.example.tasya.tablayoutrecyclerview2.model.MealsItems;
import com.example.tasya.tablayoutrecyclerview2.rest.ApiClient;
import com.example.tasya.tablayoutrecyclerview2.rest.LatestInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab2 extends Fragment {

    ArrayList<FoodDrink> mList = new ArrayList<>();
    DrinkAdapter vAdapter;
    RecyclerView recyclerView;

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
        View view2 = inflater.inflate(R.layout.tab2, container, false);

        recyclerView = view2.findViewById(R.id.recyclerView);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        //recyclerView.setLayoutManager(layoutManager);
        //mAdapter = new FoodAdapter(mList, getContext());
        //recyclerView.setAdapter(mAdapter);
        fillData();

        return view2;
    }

    private void fillData() {
        latestInterface = ApiClient.getClient().create(LatestInterface.class);
        latestItemCall = latestInterface.getData();

        latestItemCall.enqueue(new Callback<LatestItems>() {
            @Override
            public void onResponse(Call<LatestItems> call, Response<LatestItems> response) {
                mealsItem = response.body().getMealsItems();

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                vAdapter = new DrinkAdapter(mealsItem, getContext());
                recyclerView.setAdapter(vAdapter);

                vAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LatestItems> call, Throwable t) {

            }
        });


    }
}
