package com.example.tasya.tablayoutrecyclerview2.model;

import com.google.gson.annotations.SerializedName;

public class LatestItems {
    @SerializedName("meals")
    MealsItems[] mealsItems;

    public MealsItems[] getMealsItems() {
        return mealsItems;
    }

}
