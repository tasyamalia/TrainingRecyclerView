package com.example.tasya.tablayoutrecyclerview2.rest;

import com.example.tasya.tablayoutrecyclerview2.model.CategoriesItems;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesInterface {
    @GET("categories.php")
    Call<CategoriesItems> getData();

}
