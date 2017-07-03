package com.example.hlymcr.gelecegiyazanlar;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    EditText kullanici_adi, Sifre;
    Button giris, kayitOl;
    CheckBox hatirla;
    String sifree,kullaniciAd;
    Intent intent;

    //SharedPrefences tanımları
    private SharedPreferences savePreferences;
    private SharedPreferences.Editor savePrefsEditor;
    private Boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kullanici_adi = (EditText) findViewById(R.id.kullanici);
        Sifre = (EditText) findViewById(R.id.sifre);
        giris = (Button) findViewById(R.id.giris);
        hatirla = (CheckBox) findViewById(R.id.checkBox);

        savePreferences = getSharedPreferences("savePrefs",0);
        savePrefsEditor = savePreferences.edit();
        saveLogin = savePreferences.getBoolean("saveLogin", false);

        if (saveLogin == true){
            kullanici_adi.setText(savePreferences.getString("username", ""));
            Sifre.setText(savePreferences.getString("password", ""));
            hatirla.setChecked(true);
        }
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.giris:

                DBAdapter dbadapter = new DBAdapter(this);
                dbadapter.open();
                kullaniciAd = kullanici_adi.getText().toString();
                sifree = Sifre.getText().toString();
                String storedPassword = dbadapter.getSinlgeEntry(kullaniciAd);
                if(hatirla.isChecked()){
                    //hatırla checbox ına tıklandığında key olarak verilen "" string içindeki ifadelerle değerleri kaydediyoruz
                    savePrefsEditor.putBoolean("saveLogin",true);
                    savePrefsEditor.putString("username",kullaniciAd);
                    savePrefsEditor.putString("password", sifree);
                    savePrefsEditor.commit();
                    Toast.makeText(this, "Giriş bilgileriniz kaydedildi", Toast.LENGTH_SHORT).show();
                }

                else{
                    //hatırla checkbox ı disabled olduğunda preferences deki verileri siliyoruz
                    savePrefsEditor.clear();
                    savePrefsEditor.commit();
                }


                if (sifree.equals(storedPassword)) {

                    Toast.makeText(this, "Giriş başarılı", Toast.LENGTH_SHORT).show();
                    intent = new Intent(this, Main2Activity.class);
                    intent.putExtra("adi", kullaniciAd);
                    startActivity(intent);

                }

                dbadapter.close();


                break;
            case R.id.kayit:
                intent = new Intent(this, Kayit.class);
                startActivity(intent);
                break;
        }

    }

}
