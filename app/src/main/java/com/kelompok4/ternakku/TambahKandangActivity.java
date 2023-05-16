package com.kelompok4.ternakku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok4.ternakku.database.DatabaseTernaku;
import com.kelompok4.ternakku.database.TabelKandang;

public class TambahKandangActivity extends AppCompatActivity {
//    Menambahkan object edit text dan button, beserta database
    EditText namaKandang, lokasiKandang, luasKandang;
    Button simpanKandang;
    TextView judulForm;
    private DatabaseTernaku database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kandang);


//        Registrasi Object
        namaKandang = findViewById(R.id.editTextNamaKandang);
        lokasiKandang = findViewById(R.id.editTextLokasiKandang);
        luasKandang = findViewById(R.id.editTextLuasKandang);
        judulForm = findViewById(R.id.textViewJudulFormKandang);
        simpanKandang= findViewById(R.id.buttonSimpanKandang);
        database = DatabaseTernaku.getDbInstance(this.getApplicationContext());



        if(getIntent().getExtras()!= null){
            Intent hasil = getIntent();
            int id_kandang = Integer.parseInt(hasil.getStringExtra("id_kandang"));
            namaKandang.setText(hasil.getStringExtra("nama_kandang"));
            lokasiKandang.setText(hasil.getStringExtra("lokasi_kandang"));
            luasKandang.setText(hasil.getStringExtra("luas_kandang"));
            judulForm.setText("Form Perbarui Kandang");
            simpanKandang.setText("Perbarui Data");
            simpanKandang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //        Validasi Input
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
                        updateData(id_kandang);
                    }
                }
            });

        }else{
            simpanKandang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //        Validasi Input
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
                        simpanData();
                    }
                }
            });
        }
    }


    public void  simpanData(){
        AlertDialog.Builder infoMsg = new AlertDialog.Builder(TambahKandangActivity.this);
        infoMsg.setTitle("Apakah Anda Yakin?");
        infoMsg.setMessage("Apakah anda yakin ingin menginputkan data sebagai berikut?\n\n"+"" +
                "Nama Kandang: " + namaKandang.getText().toString() +"\n"+
                "Lokasi Kandang: " + lokasiKandang.getText().toString() +"\n"+
                "Luas Kandang: " + luasKandang.getText().toString() + " Meter Persegi \n");
        infoMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String namaKandangVar = namaKandang.getText().toString();
                String lokasiKandangVar = lokasiKandang.getText().toString();
                String luasKandangVar = luasKandang.getText().toString();
                int luasKandangVarPar = Integer.parseInt(luasKandangVar);
                TabelKandang row = new TabelKandang(namaKandangVar,lokasiKandangVar,luasKandangVarPar);
                database.daoHewan().insertDataKandang(row);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("nama_kandang", namaKandangVar);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        infoMsg.setNegativeButton("Perbarui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                namaKandang.requestFocus();
                dialogInterface.dismiss();
            }
        });
        infoMsg.show();
    }

    public void  updateData(int id_kandang){

        AlertDialog.Builder infoMsg = new AlertDialog.Builder(TambahKandangActivity.this);
        infoMsg.setTitle("Apakah Anda Yakin?");
        infoMsg.setMessage("Apakah anda yakin ingin memperbarui data sebagai berikut?\n\n"+"" +
                "Nama Kandang: " + namaKandang.getText().toString() +"\n"+
                "Lokasi Kandang: " + lokasiKandang.getText().toString() +"\n"+
                "Luas Kandang: " + luasKandang.getText().toString() + " Meter Persegi \n");
        infoMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String namaKandangVar = namaKandang.getText().toString();
                String lokasiKandangVar = lokasiKandang.getText().toString();
                String luasKandangVar = luasKandang.getText().toString();
                int luasKandangVarPar = Integer.parseInt(luasKandangVar);
                TabelKandang row = new TabelKandang(namaKandangVar,lokasiKandangVar,luasKandangVarPar);
                row.setId(id_kandang);
                database.daoHewan().updateKandang(row);
                finish();
            }
        });
        infoMsg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                namaKandang.requestFocus();
                dialogInterface.dismiss();
            }
        });
        infoMsg.show();
    }

}


