package com.example.tasya.tablayoutrecyclerview2.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FavoriteModel extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String nama;
    private String gambar;
    private String kategori;
    private String jenis;

    private String instruction;

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
