package com.example.tasya.tablayoutrecyclerview2;

import android.graphics.drawable.Drawable;

public class FoodDrink {
    public String title;
    public Drawable gambar;
    public String desc;
    public String times;


    public FoodDrink(String title, Drawable gambar, String desc, String times) {
        this.title = title;
        this.gambar = gambar;
        this.desc = desc;
        this.times = times;
    }

}
