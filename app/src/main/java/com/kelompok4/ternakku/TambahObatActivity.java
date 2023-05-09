package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TambahObatActivity extends AppCompatActivity {
    //    Menambahkan object edit text dan button
    EditText namaObat, jumlahObat, deskripsiObat,aturanObat;
    Button simpanObat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_obat);
//        Registrasi Object
        namaObat = findViewById(R.id.editTextNamaObat);
        jumlahObat = findViewById(R.id.editTextJumlahObat);
        deskripsiObat = findViewById(R.id.editTextDeskripsiObat);
        aturanObat= findViewById(R.id.editTextAturanObat);
        simpanObat = findViewById(R.id.buttonSimpanObat);
//        Validasi Input
        simpanObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(namaObat.getText().toString().trim().length()==0){
                    namaObat.setError("Nama Obat harus di isi!");
                    namaObat.requestFocus();
                }else if(jumlahObat.getText().toString().trim().length()==0){
                    jumlahObat.setError("Jumlah Obat Harus di isi!");
                    jumlahObat.requestFocus();
                }else if(deskripsiObat.getText().toString().trim().length()==0){
                    deskripsiObat.setError("Deskripsi Obat Harus Di isi!");
                    deskripsiObat.requestFocus();
                }else if(aturanObat.getText().toString().trim().length()==0){
                    aturanObat.setError("Aturan Obat Harus Di isi!");
                    aturanObat.requestFocus();
                }else {
                    Intent detail= new Intent(TambahObatActivity.this,DetailObatActivity.class);
                    detail.putExtra("nama_obat",namaObat.getText().toString());
                    detail.putExtra("jumlah_obat",jumlahObat.getText().toString());
                    detail.putExtra("deskripsi_obat",deskripsiObat.getText().toString());
                    detail.putExtra("aturan_obat",aturanObat.getText().toString());
                    startActivity(detail);
                }
            }
        });
    }
}