package com.example.tasya.tablayoutrecyclerview2;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Add extends RealmObject {
    @PrimaryKey
    private int id;
    private String nama;
    private Double latitude;
    private Double longitude;
    private String image_lok;

    public String getImage_lok() {
        return image_lok;
    }

    public void setImage_lok(String image_lok) {
        this.image_lok = image_lok;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
