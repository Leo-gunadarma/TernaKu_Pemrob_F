package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailObatActivity extends AppCompatActivity {
    //    Menambahkan object textView
    TextView detailObat,detailJumlahObat,detailDeskripsiObat,detailAturanObat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_obat);
        //      Registrasi Object
        detailObat = findViewById(R.id.textViewResultNamaObat);
        detailJumlahObat = findViewById(R.id.textViewResultJumlahObat);
        detailDeskripsiObat= findViewById(R.id.textViewResultDeskripsiObat);
        detailAturanObat= findViewById(R.id.textViewResultAturanObat);


        if (getIntent().getExtras()!= null) {
//        Mengambil nilai intent dan memasukannya kedalam text view
            Intent hasil = getIntent();
            detailObat.setText(hasil.getStringExtra("nama_obat"));
            detailJumlahObat.setText(hasil.getStringExtra("jumlah_obat"));
            detailDeskripsiObat.setText(hasil.getStringExtra("deskripsi_obat"));
            detailAturanObat.setText(hasil.getStringExtra("aturan_obat"));
        }
    }
}