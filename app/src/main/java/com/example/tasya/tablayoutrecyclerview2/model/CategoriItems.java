package com.example.tasya.tablayoutrecyclerview2.model;

import com.google.gson.annotations.SerializedName;

public class CategoriItems {
    @SerializedName("strCategory")
    public String strCategory;

    @SerializedName("strCategoryThumb")
    public String strCategoryThumb;

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

}
