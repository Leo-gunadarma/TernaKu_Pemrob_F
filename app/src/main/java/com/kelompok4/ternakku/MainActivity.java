package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView kelolaHewan,kelolaKandang,kelolaObat,kelolaPengaturan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kelolaHewan = findViewById(R.id.imageViewHewan);
        kelolaKandang = findViewById(R.id.imageViewKandang);
        kelolaObat = findViewById(R.id.imageViewObat);
        kelolaPengaturan = findViewById(R.id.imageViewPengaturan);

        kelolaHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuKelolaHewan();
            }
        });
    }
    public void menuKelolaHewan(){
        Intent intent = new Intent(MainActivity.this, TambahHewanActivity.class);
        startActivity(intent);
    }
    public void menuKelolaKandang(){
//        Intent intent = new Intent(MainActivity.this, KelolaKandangActivity.class);
//        startActivity(intent);
    }
    public void menuKelolaObat(){
//        Intent intent = new Intent(MainActivity.this, KelolaObatActivity.class);
//        startActivity(intent);
    }
    public void menuPengaturanProfil(){
//        Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}