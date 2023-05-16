package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailKandangActivity extends AppCompatActivity {
    //    Menambahkan object textView
    TextView detailKandang,detailLokasi,detailLuas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kandang);
//      Registrasi Object
        detailKandang = findViewById(R.id.textViewResultNamaKandang);
        detailLokasi = findViewById(R.id.textViewResultLokasiKandang);
        detailLuas= findViewById(R.id.textViewResultLuasKandang);

//        Mengambil nilai intent dan memasukannya kedalam text view
        if (getIntent().getExtras()!= null){
            Intent hasil = getIntent();
            detailKandang.setText(hasil.getStringExtra("nama_kandang"));
            detailLokasi.setText(hasil.getStringExtra("lokasi_kandang"));
            detailLuas.setText(hasil.getStringExtra("luas_kandang"));
        }

    }
}