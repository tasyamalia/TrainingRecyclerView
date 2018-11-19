package com.example.tasya.tablayoutrecyclerview2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AddActivity extends AppCompatActivity {
    EditText edt_nama, edt_latitude, edt_longitude;
    public static final int REQUEST_IMAGE = 100;
    public static final int REQUEST_PERMISSION = 200;
    Realm realm;
    RealmHelper realmHelper;
    Button button, btnpic;
    private ImageView imageView;
    private String imageFilePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Point");

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        edt_nama = findViewById(R.id.edt_nama);
        edt_latitude = findViewById(R.id.edt_latitude);
        edt_longitude = findViewById(R.id.edt_longitude);

        edt_latitude.setText(String.valueOf(getIntent().getExtras().getDouble("LATITUDE")));
        edt_longitude.setText(String.valueOf(getIntent().getExtras().getDouble("LONGITUDE")));

        button = findViewById(R.id.button);
        btnpic = findViewById(R.id.btnpic);
        imageView = findViewById(R.id.image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Add add = new Add();
                    add.setNama(edt_nama.getText().toString());
                    add.setLatitude(Double.parseDouble(edt_latitude.getText().toString()));
                    add.setLongitude(Double.parseDouble(edt_longitude.getText().toString()));
                    add.setImage_lok(imageFilePath);
                    realmHelper = new RealmHelper(realm);
                    realmHelper.save_add(add);
                    Toast.makeText(getApplicationContext(), "Point Added", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(), "Meal already added before", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AddActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddActivity.this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, REQUEST_PERMISSION);
                } else {
                    openCameraIntent();
                }
            }
        });
    }

    private void openCameraIntent() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            Uri photoUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", photoFile);
            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(pictureIntent, REQUEST_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Thanks for granting permission", Toast.LENGTH_SHORT).show();
                openCameraIntent();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(Uri.parse(imageFilePath));
                Log.d("FilePath", imageFilePath);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You canceled the operation", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        //File direct = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        //TODO 11 Cara agara foto bisa disave di directory pilihan kita
        String direct = Environment.getExternalStorageDirectory() + "/PesanMakan";
        File folder = new File(direct);
        if (!folder.exists()) {
            File Directory = new File(direct);
            Directory.mkdirs();
        }
        File image = File.createTempFile(imageFileName, ".jpg", folder);
        imageFilePath = image.getAbsolutePath();

        return image;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
