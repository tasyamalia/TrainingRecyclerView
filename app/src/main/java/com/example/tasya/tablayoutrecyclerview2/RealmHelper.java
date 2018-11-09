package com.example.tasya.tablayoutrecyclerview2;

import android.util.Log;

import com.example.tasya.tablayoutrecyclerview2.model.FavoriteModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final FavoriteModel favoriteModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "DB was created");
                    Number currentIdNum = realm.where(FavoriteModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    favoriteModel.setId(nextId);
                    FavoriteModel model = realm.copyToRealm(favoriteModel);
                } else {
                    Log.e("", "DB not Found");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<FavoriteModel> getFavorite() {
        RealmResults<FavoriteModel> results = realm.where(FavoriteModel.class).findAll();
        return results;
    }

    // untuk meng-update data
    public void update(final Integer id, final String nama, final String gambar, final String kategori, final String jenis) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FavoriteModel model = realm.where(FavoriteModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setNama(nama);
                model.setGambar(gambar);
                model.setKategori(kategori);
                model.setJenis(jenis);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("", "onSuccess: Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }


    // untuk menghapus data
    public void delete(Integer id) {
        final RealmResults<FavoriteModel> model = realm.where(FavoriteModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

    //save data Add
    public void save_add(final Add add) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "DB was created");
                    Number currentIdNum = realm.where(Add.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    add.setId(nextId);
                    realm.copyToRealm(add);
                } else {
                    Log.e("", "DB not Found");
                }
            }
        });
    }

    public List<Add> getAllAdd() {
        RealmResults<Add> results = realm.where(Add.class).findAll();
        return results;
    }
}
