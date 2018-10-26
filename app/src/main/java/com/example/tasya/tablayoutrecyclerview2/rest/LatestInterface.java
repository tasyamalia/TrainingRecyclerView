package com.example.tasya.tablayoutrecyclerview2.rest;

import com.example.tasya.tablayoutrecyclerview2.model.LatestItems;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LatestInterface {
    @GET("latest.php")
    Call<LatestItems> getData();
}
