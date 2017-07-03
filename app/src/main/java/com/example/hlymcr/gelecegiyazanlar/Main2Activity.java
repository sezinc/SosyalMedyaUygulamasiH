package com.example.hlymcr.gelecegiyazanlar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    Intent intent2;
    ImageView img;
    TextView name, tarih;
    ListView listView;
    String user;
    String adi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        Bundle b=intent.getExtras();
        user=b.getString("user");

        img = (ImageView) findViewById(R.id.resim);
        name = (TextView) findViewById(R.id.isim);
        tarih = (TextView) findViewById(R.id.dogum);
        listView = (ListView) findViewById(R.id.lv);

        //kullanıcı ismini intent le çektik
        intent =getIntent();
        adi = intent.getStringExtra("adi");
        name.setText(adi);

    }

    public void profilGoster(View v)

    {
        DBAdapter dbadapter = new DBAdapter(this);
        dbadapter.open();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("İnternet Sitesi");

        //getUrl ile kullanıcı ismine karşılık gelen url bilgisini alıyoruz
        String url =dbadapter.getUrl(adi);

        Log.d("urll",url);

        WebView wv = new WebView(this);

        wv.loadUrl(url);

        wv.setWebViewClient(new WebViewClient() {

            @Override

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);


                return true;

            }

        });


        alert.setView(wv);

        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();

            }

        });

        alert.show();

        dbadapter.close();

    }

    //Geri tuşuna bastığımızda profilden çıkıyoruz
    public void onBackPressed() {

        AlertDialog.Builder alert = new AlertDialog.Builder(Main2Activity.this);

        alert.setTitle("Çıkış");

        alert.setMessage("Çıkmak istediğinden emin misin?");


        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                intent2 = new Intent(Main2Activity.this, MainActivity.class);

                startActivity(intent2);

            }

        });

        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();

            }

        });

        alert.show();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // Action Bar içinde kullanılacak menü öğelerini inflate edelim
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
        
            
        
    }
    //Action bar bu kısımda

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Action Bar öğelerindeki basılmaları idare edelim
        switch (item.getItemId()) {
            case R.id.arama:
                openSearch();
                return true;
            case R.id.düzenleme:
                openSettings();
            return true;
            default:
        return super.onOptionsItemSelected(item);
        }
    }

    private void openSettings() {
    }

    private void openSearch() {
    }
    
}
