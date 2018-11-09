package com.example.tasya.tablayoutrecyclerview2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
    SwipeRefreshLayout sw;

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
        sw = view2.findViewById(R.id.swLayout);
        //Set up Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        //tampil semua data favorite
        favoriteModel = realmHelper.getFavorite();
        vAdapter = new AdapterFavorite(getActivity(), favoriteModel);
        recyclerView.setAdapter(vAdapter);

        //Mengeset listener yang akan dijalankan saat di refresh
        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //handler untuk menjalankan jeda selama 2 detik
//                new Handler().postDelayed(new Runnable(){
//                    @Override
//                    public void run(){
//                        sw.setRefreshing(false);//berhenti ketika data sudah di load
//
//                        //fungsi yang dijalankan saat di refresh
//                        favoriteModel = realmHelper.getFavorite();
//                        vAdapter = new AdapterFavorite(getActivity(), favoriteModel);
//                        recyclerView.setAdapter(vAdapter);
//                    }
//                }, 2000);
            }
        });
        return view2;
    }

    //lifecycle ,biar bisa langsung refresh saat ditekan tab nya
    @Override
    public void onResume() {
        super.onResume();
        sw.setRefreshing(false);
        favoriteModel = realmHelper.getFavorite();
        vAdapter = new AdapterFavorite(getActivity(), favoriteModel);
        recyclerView.setAdapter(vAdapter);
    }
}
