package com.example.tasya.tablayoutrecyclerview2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tasya.tablayoutrecyclerview2.model.FavoriteModel;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);

        //untuk back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        final Bundle bundle = getIntent().getExtras();

        ImageView img1 = findViewById(R.id.image1);
        TextView tv1 = findViewById(R.id.tvJudul);
        TextView tv2 = findViewById(R.id.tvCat);
        final ImageView img2 = findViewById(R.id.image2);
        String instruction = getIntent().getExtras().getString("Instruction");
        //TextView textView = findViewById(R.id.tvInst);
        //textView.setText(instruction);

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img2.setColorFilter(getApplication().getResources().getColor(R.color.colorFavorite));
                FavoriteModel favoriteModel = new FavoriteModel();
                favoriteModel.setGambar(bundle.getString("MealThumb"));
                favoriteModel.setNama(bundle.getString("Title"));
                favoriteModel.setKategori(bundle.getString("Category"));
                //favoriteModel.setInstruction(bundle.getString("instruction"));
                realmHelper.save(favoriteModel);
            }
        });

        tv1.setText(bundle.getString("Title"));
        tv2.setText(bundle.getString("Category"));
        //textView.setText(bundle.getString("instruction"));
        Picasso.get().load(bundle.getString("MealThumb")).into(img1);

//        Toast.makeText(getApplicationContext(),bundle.getString("Title"),Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),bundle.getString("Category"),Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}