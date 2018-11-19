package com.example.tasya.tablayoutrecyclerview2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    EditText nama, email, alamat;
    TextView nama2, email2, alamat2;

    String namaS, emailS, alamatS;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        nama = findViewById(R.id.tvNama);
        email = findViewById(R.id.tvEmail);
        alamat = findViewById(R.id.tvAlamat);
        Button save_data = findViewById(R.id.btnSave);
        final LinearLayout l = findViewById(R.id.linear1);
        final LinearLayout ll = findViewById(R.id.linear2);
        nama2 = findViewById(R.id.tvNama2);
        email2 = findViewById(R.id.tvEmail2);
        alamat2 = findViewById(R.id.tvAlamat2);
        Button show_data = findViewById(R.id.btnShow);

        namaS = Preferences.getNama(getApplicationContext());
        nama.setText(namaS);
        emailS = Preferences.getEmail((getApplicationContext()));
        email.setText(emailS);
        alamatS = Preferences.getAlamat((getApplicationContext()));
        alamat.setText(alamatS);

        //checkFilled();
        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!nama.getText().toString().equals("") &&
                        !email.getText().toString().equals("") && !alamat.getText().toString().equals("")) {
                    //Toast.makeText(UserProfile.this, "Save data success!!!!!!!", Toast.LENGTH_SHORT).show();
                    //nama.setText("");//ini membuat hilang nama
                    Preferences.setNama(getApplicationContext(), nama.getText().toString());
                    Preferences.setEmail(getApplicationContext(), email.getText().toString());
                    Preferences.setAlamat(getApplicationContext(), alamat.getText().toString());
                    //Preferences.setFIlled(getApplicationContext(),true);
                }
                l.setVisibility(View.GONE);
                ll.setVisibility(View.VISIBLE);
                namaS = Preferences.getNama(getApplicationContext());
                nama2.setText(namaS);
                emailS = Preferences.getEmail((getApplicationContext()));
                email2.setText(emailS);
                alamatS = Preferences.getAlamat((getApplicationContext()));
                alamat2.setText(alamatS);

            }
        });

        show_data.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             ll.setVisibility(View.GONE);
                                             l.setVisibility(View.VISIBLE);
                                             String namaa = Preferences.getNama(getApplicationContext());
                                             nama.setText(namaa);
                                             String emaill = Preferences.getEmail((getApplicationContext()));
                                             email.setText(emaill);
                                             String alamatt = Preferences.getAlamat((getApplicationContext()));
                                             alamat.setText(alamatt);
                                         }
                                     }
        );
    }
    //void checkFilled(){ //Kenapa void?krena tidak mereturn value
    //if(Preferences.getFilled(getApplicationContext())){
    //nama.setText(Preferences.getNama(getApplicationContext()));
    //nama.setEnabled(false);
    //email.setText(Preferences.getEmail(getApplicationContext()));
    //email.setEnabled(false);
    //alamat.setText(Preferences.getAlamat(getApplicationContext()));
    //alamat.setEnabled(false);
    //}
    //}

}
