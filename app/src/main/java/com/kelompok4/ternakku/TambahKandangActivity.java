package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TambahKandangActivity extends AppCompatActivity {
//    Menambahkan object edit text dan button
    EditText namaKandang, lokasiKandang, luasKandang;
    Button simpanKandang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kandang);

//        Registrasi Object
        namaKandang = findViewById(R.id.editTextNamaKandang);
        lokasiKandang = findViewById(R.id.editTextLokasiKandang);
        luasKandang = findViewById(R.id.editTextLuasKandang);
        simpanKandang= findViewById(R.id.buttonSimpanKandang);

//        Validasi Input
        simpanKandang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(namaKandang.getText().toString().trim().length()==0){
                    namaKandang.setError("Nama Kandang harus di isi!");
                    namaKandang.requestFocus();
                }else if(lokasiKandang.getText().toString().trim().length()==0){
                    lokasiKandang.setError("Lokasi Kandang Harus di isi!");
                    lokasiKandang.requestFocus();
                }else if(luasKandang.getText().toString().trim().length()==0){
                    luasKandang.setError("Luas Kandang Harus Di isi!");
                    luasKandang.requestFocus();
                }else {
                    Intent detail= new Intent(TambahKandangActivity.this,DetailKandangActivity.class);
                    detail.putExtra("nama_kandang",namaKandang.getText().toString());
                    detail.putExtra("lokasi_kandang",lokasiKandang.getText().toString());
                    detail.putExtra("luas_kandang",luasKandang.getText().toString());
                    startActivity(detail);
                }
            }
        });
    }
}