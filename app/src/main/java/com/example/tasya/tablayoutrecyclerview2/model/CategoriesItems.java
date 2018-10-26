package com.example.tasya.tablayoutrecyclerview2.model;

import com.google.gson.annotations.SerializedName;

public class CategoriesItems {
    @SerializedName("categories")
    CategoriItems[] categoriItems;

    public CategoriItems[] getCategoriItems() {
        return categoriItems;
    }
}
