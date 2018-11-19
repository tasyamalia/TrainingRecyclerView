package com.example.tasya.tablayoutrecyclerview2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    static final String NAMA = "Nama";
    static final String EMAIL = "Email";
    static final String ALAMAT = "Alamat";

    /**
     * Pendlakarasian Shared Preferences yang berdasarkan paramater context
     */
    public static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setNama(Context context, String nama) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(NAMA, nama);
        editor.apply();
    }

    public static String getNama(Context context) {
        return getSharedPreference(context).getString(NAMA, "");
    }

    public static void setEmail(Context context, String email) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public static String getEmail(Context context) {
        return getSharedPreference(context).getString(EMAIL, "");
    }

    public static void setAlamat(Context context, String alamat) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(ALAMAT, alamat);
        editor.apply();
    }

    public static String getAlamat(Context context) {
        return getSharedPreference(context).getString(ALAMAT, "");
    }

    //public static void setFIlled(Context context, Boolean filled){  //Untuk mengecek apakah field sudah terisi
    //SharedPreferences.Editor editor = getSharedPreference(context).edit();
    //editor.putBoolean(FILLED, filled);
    //editor.apply();
    //}
    //public static Boolean getFilled(Context context){ //karena kalau true tandanya sudah disetting  kalau false belum disetting
    //return getSharedPreference(context).getBoolean(FILLED,false);
    //}
}
