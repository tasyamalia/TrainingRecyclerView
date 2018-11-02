package com.example.tasya.tablayoutrecyclerview2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tasya.tablayoutrecyclerview2.model.FavoriteModel;
import com.example.tasya.tablayoutrecyclerview2.model.LatestItems;
import com.example.tasya.tablayoutrecyclerview2.model.MealsItems;
import com.example.tasya.tablayoutrecyclerview2.rest.LatestInterface;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;

public class Tab2 extends Fragment {

    ArrayList<FoodDrink> mList = new ArrayList<>();
    AdapterFavorite vAdapter;
    RecyclerView recyclerView;

    LatestInterface latestInterface;
    Call<LatestItems> latestItemCall;
    MealsItems[] mealsItem;
    Realm realm;
    RealmHelper realmHelper;
    List<FavoriteModel> favoriteModel;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //mAdapter = new FoodAdapter(mList, getContext());
        //recyclerView.setAdapter(mAdapter);

        //Set up Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        favoriteModel = realmHelper.getFavorite();
        vAdapter = new AdapterFavorite(getActivity(), favoriteModel);
        recyclerView.setAdapter(vAdapter);
        return view2;
    }


}
